package zandb.software.swipeit.data.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zandb.software.swipeit.data.user.Supplier;
import zandb.software.swipeit.data.user.dto.SupplierDTO;
import zandb.software.swipeit.data.user.exceptions.UserNotFoundException;
import zandb.software.swipeit.data.user.repository.SupplierRepository;
import zandb.software.swipeit.security.AccessDeniedException;

@Service
public class SupplierService {

  @Autowired
  private SupplierRepository supplierRepository;

  @Autowired
  private SwipeItUserDetailsService swipeItUserDetailsService;

  @Autowired
  private ModelMapper modelMapper;

  public void createSupplierWithUsernameAndPassword(String username, String password) {
    Supplier supplier = new Supplier();
    supplier.setUsername(username);
    supplier.setPassword(password);

    supplierRepository.saveAndFlush(supplier);
  }

  public SupplierDTO updateClient(SupplierDTO supplierDTO)
      throws UserNotFoundException, AccessDeniedException {
    final long id = swipeItUserDetailsService.getIdOfLoggedInUser();

    if (!supplierRepository.existsById(id)) {
      throw new UserNotFoundException("There is no user with the id %d".formatted(id));
    }

    supplierDTO.setUserId(id);
    supplierDTO.setUsername(swipeItUserDetailsService.getUsernameOfLoggedInuser());

    return modelMapper.map(
        supplierRepository.saveAndFlush(modelMapper.map(supplierDTO, Supplier.class)),
        SupplierDTO.class);
  }

}
