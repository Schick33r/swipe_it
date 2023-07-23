package zandb.software.swipeit.data.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zandb.software.swipeit.data.user.Client;
import zandb.software.swipeit.data.user.dto.ClientDTO;
import zandb.software.swipeit.data.user.exceptions.UserNotFoundException;
import zandb.software.swipeit.data.user.repository.ClientRepository;
import zandb.software.swipeit.security.AccessDeniedException;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private SwipeItUserDetailsService swipeItUserDetailsService;

  public void createClientWithUsernameAndPassword(String username, String password) {
    Client client = new Client();
    client.setUsername(username);
    client.setPassword(password);

    clientRepository.saveAndFlush(client);
  }

  public ClientDTO updateClient(ClientDTO clientDTO)
      throws UserNotFoundException, AccessDeniedException {
    final long id = swipeItUserDetailsService.getIdOfLoggedInUser();

    if (!clientRepository.existsById(id)) {
      throw new UserNotFoundException("There is no user with the id %d".formatted(id));
    }

    clientDTO.setUserId(id);
    clientDTO.setUsername(swipeItUserDetailsService.getUsernameOfLoggedInuser());

    return modelMapper.map(clientRepository.saveAndFlush(modelMapper.map(clientDTO, Client.class)),
        ClientDTO.class);
  }

}
