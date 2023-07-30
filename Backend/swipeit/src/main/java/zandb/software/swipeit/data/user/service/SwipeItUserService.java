package zandb.software.swipeit.data.user.service;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zandb.software.swipeit.data.user.SwipeItUser;
import zandb.software.swipeit.data.user.dto.SwipeItUserDTO;
import zandb.software.swipeit.data.user.exceptions.UserNotFoundException;
import zandb.software.swipeit.data.user.repository.SwipeItUserRepository;
import zandb.software.swipeit.data.user.type.UserType;
import zandb.software.swipeit.security.AccessDeniedException;

@Service
public class SwipeItUserService {

  @Autowired
  private SwipeItUserRepository swipeItUserRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private SwipeItUserDetailsService swipeItUserDetailsService;

  public void createUserWithUsernameAndPassword(String username, String password) {
    createUserWithUsernameAndPassword(username, password, UserType.CLIENT);
  }

  public void createUserWithUsernameAndPassword(String username, String password,
      UserType userType) {
    SwipeItUser swipeItUser = new SwipeItUser();
    swipeItUser.setUsername(username);
    swipeItUser.setPassword(password);
    swipeItUser.setUserType(userType.getType());

    swipeItUserRepository.saveAndFlush(swipeItUser);
  }

  public SwipeItUserDTO getUser() throws UserNotFoundException, AccessDeniedException {
    final long id = swipeItUserDetailsService.getIdOfLoggedInUser();
    Optional<SwipeItUser> userOptional = swipeItUserRepository.findById(id);

    if (userOptional.isEmpty()) {
      throw new UserNotFoundException("There is no user with the id %d".formatted(id));
    }

    return modelMapper.map(userOptional.get(), SwipeItUserDTO.class);
  }

  public SwipeItUserDTO updateUser(SwipeItUserDTO swipeItUserDTO)
      throws UserNotFoundException {
    final long id = swipeItUserDetailsService.getIdOfLoggedInUser();

    if (!swipeItUserRepository.existsById(id)) {
      throw new UserNotFoundException("There is no user with the id %d".formatted(id));
    }

    SwipeItUser swipeItUser = swipeItUserRepository.getReferenceById(id);

    swipeItUserDTO.setUserId(id);
    swipeItUserDTO.setUsername(swipeItUserDetailsService.getUsernameOfLoggedInuser());

    SwipeItUser swipeItUserUpdated = modelMapper.map(swipeItUserDTO, SwipeItUser.class);

    swipeItUserUpdated.setPassword(swipeItUser.getPassword());

    return modelMapper.map(
        swipeItUserRepository.saveAndFlush(swipeItUserUpdated),
        SwipeItUserDTO.class);
  }

}
