package zandb.software.swipeit.data.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zandb.software.swipeit.data.user.Client;
import zandb.software.swipeit.data.user.Supplier;
import zandb.software.swipeit.data.user.dto.ClientDTO;
import zandb.software.swipeit.data.user.dto.SupplierDTO;
import zandb.software.swipeit.data.user.dto.SwipeItUserDTO;
import zandb.software.swipeit.data.user.exceptions.UserNotFoundException;
import zandb.software.swipeit.data.user.repository.ClientRepository;
import zandb.software.swipeit.data.user.repository.SupplierRepository;
import zandb.software.swipeit.security.AccessDeniedException;

import java.util.Optional;

@Service
public class SwipeItUserService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SwipeItUserDetailsService swipeItUserDetailsService;

    public SwipeItUserDTO getClient() throws UserNotFoundException, AccessDeniedException {
        final long id = swipeItUserDetailsService.getIdOfLoggedInUser();
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isPresent()) {
            return modelMapper.map(clientOptional.get(), ClientDTO.class);
        }

        Optional<Supplier> supplierOptional = supplierRepository.findById(id);

        if (supplierOptional.isEmpty()) {
            throw new UserNotFoundException("There is no user with the id %d".formatted(id));
        }

        return modelMapper.map(supplierOptional.get(), SupplierDTO.class);
    }
}
