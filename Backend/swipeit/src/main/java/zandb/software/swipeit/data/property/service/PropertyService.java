package zandb.software.swipeit.data.property.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zandb.software.swipeit.data.property.Property;
import zandb.software.swipeit.data.property.dto.PropertyDTO;
import zandb.software.swipeit.data.property.exceptions.PropertyDoesNotBelongToLoggedInUser;
import zandb.software.swipeit.data.property.exceptions.PropertyNotFoundException;
import zandb.software.swipeit.data.property.repository.PropertyRepository;
import zandb.software.swipeit.data.user.Supplier;
import zandb.software.swipeit.data.user.repository.SupplierRepository;
import zandb.software.swipeit.data.user.service.SwipeItUserDetailsService;
import zandb.software.swipeit.data.user.type.UserType;
import zandb.software.swipeit.security.UserIsNotSupplierException;

@Service
public class PropertyService {

  @Autowired
  private SwipeItUserDetailsService swipeItUserDetailsService;

  @Autowired
  private SupplierRepository supplierRepository;

  @Autowired
  private PropertyRepository propertyRepository;

  @Autowired
  private ModelMapper modelMapper;


  public PropertyDTO createProperty(PropertyDTO propertyDTO) throws UserIsNotSupplierException {
    if (!swipeItUserDetailsService.getTypeOfLoggedInUser().equals(UserType.SUPPLIER.getType())) {
      throw new UserIsNotSupplierException(
          "This user is no supplier and can't add any properties!");
    }

    final long id = swipeItUserDetailsService.getIdOfLoggedInUser();

    Supplier supplier = supplierRepository.getReferenceById(id);

    Property property = modelMapper.map(propertyDTO, Property.class);

    property.setOwner(supplier);

    return modelMapper.map(propertyRepository.saveAndFlush(property), PropertyDTO.class);
  }

  public PropertyDTO updateProperty(long propertyId, PropertyDTO propertyDTO)
      throws UserIsNotSupplierException, PropertyNotFoundException, PropertyDoesNotBelongToLoggedInUser {
    final long id = getIdOfLoggedInUserAndCheckIfPropertyCanBeUpdatedOrDeleted(propertyId);

    Supplier supplier = supplierRepository.getReferenceById(id);

    Property property = modelMapper.map(propertyDTO, Property.class);

    property.setOwner(supplier);

    return modelMapper.map(propertyRepository.saveAndFlush(property), PropertyDTO.class);
  }

  public void deleteProperty(long propertyId)
      throws UserIsNotSupplierException, PropertyNotFoundException, PropertyDoesNotBelongToLoggedInUser {
    getIdOfLoggedInUserAndCheckIfPropertyCanBeUpdatedOrDeleted(propertyId);

    propertyRepository.deleteById(propertyId);
  }

  private long getIdOfLoggedInUserAndCheckIfPropertyCanBeUpdatedOrDeleted(long propertyId)
      throws UserIsNotSupplierException, PropertyNotFoundException, PropertyDoesNotBelongToLoggedInUser {
    if (!swipeItUserDetailsService.getTypeOfLoggedInUser().equals(UserType.SUPPLIER.getType())) {
      throw new UserIsNotSupplierException(
          "This user is no supplier and can therefore not manipulate properties!");
    }

    if (!propertyRepository.existsById(propertyId)) {
      throw new PropertyNotFoundException(
          "The property with the id %d doesn't exist!".formatted(propertyId));
    }

    final long id = swipeItUserDetailsService.getIdOfLoggedInUser();

    Property oldProperty = propertyRepository.getReferenceById(propertyId);

    if (oldProperty.getOwner().getUserId() != id) {
      throw new PropertyDoesNotBelongToLoggedInUser(
          "The property with the id %d does not belong to the logged in user and can therefore not be update".formatted(
              id));
    }
    return id;
  }
}
