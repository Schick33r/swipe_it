package zandb.software.swipeit.data.property.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zandb.software.swipeit.data.property.Property;
import zandb.software.swipeit.data.property.dto.PropertyDTO;
import zandb.software.swipeit.data.property.exceptions.PropertyDoesNotBelongToLoggedInUser;
import zandb.software.swipeit.data.property.exceptions.PropertyNotFoundException;
import zandb.software.swipeit.data.property.repository.PropertyRepository;
import zandb.software.swipeit.data.user.SwipeItUser;
import zandb.software.swipeit.data.user.exceptions.UserNotFoundException;
import zandb.software.swipeit.data.user.repository.SwipeItUserRepository;
import zandb.software.swipeit.data.user.service.SwipeItUserDetailsService;

@Service
public class PropertyService {

  @Autowired
  private SwipeItUserDetailsService swipeItUserDetailsService;

  @Autowired
  private SwipeItUserRepository swipeItUserRepository;

  @Autowired
  private PropertyRepository propertyRepository;

  @Autowired
  private ModelMapper modelMapper;


  public PropertyDTO createProperty(PropertyDTO propertyDTO)
      throws UserNotFoundException {
    final long id = swipeItUserDetailsService.getIdOfLoggedInUser();

    if (swipeItUserRepository.existsById((id))) {
      throw new UserNotFoundException("There is no user with the id %d".formatted(id));
    }

    SwipeItUser supplier = swipeItUserRepository.getReferenceById(id);

    Property property = modelMapper.map(propertyDTO, Property.class);

    property.setOwner(supplier);

    return modelMapper.map(propertyRepository.saveAndFlush(property), PropertyDTO.class);
  }

  public PropertyDTO updateProperty(long propertyId, PropertyDTO propertyDTO)
      throws PropertyNotFoundException, PropertyDoesNotBelongToLoggedInUser, UserNotFoundException {
    final long id = getIdOfLoggedInUserAndCheckIfPropertyCanBeUpdatedOrDeleted(propertyId);

    SwipeItUser supplier = swipeItUserRepository.getReferenceById(id);

    Property property = modelMapper.map(propertyDTO, Property.class);

    property.setOwner(supplier);

    return modelMapper.map(propertyRepository.saveAndFlush(property), PropertyDTO.class);
  }

  public void deleteProperty(long propertyId)
      throws PropertyNotFoundException, PropertyDoesNotBelongToLoggedInUser, UserNotFoundException {
    getIdOfLoggedInUserAndCheckIfPropertyCanBeUpdatedOrDeleted(propertyId);

    propertyRepository.deleteById(propertyId);
  }

  private long getIdOfLoggedInUserAndCheckIfPropertyCanBeUpdatedOrDeleted(long propertyId)
      throws PropertyNotFoundException, PropertyDoesNotBelongToLoggedInUser, UserNotFoundException {
    if (!propertyRepository.existsById(propertyId)) {
      throw new PropertyNotFoundException(
          "The property with the id %d doesn't exist!".formatted(propertyId));
    }

    final long id = swipeItUserDetailsService.getIdOfLoggedInUser();

    if (swipeItUserRepository.existsById((id))) {
      throw new UserNotFoundException("There is no user with the id %d".formatted(id));
    }

    Property oldProperty = propertyRepository.getReferenceById(propertyId);

    if (oldProperty.getOwner().getUserId() != id) {
      throw new PropertyDoesNotBelongToLoggedInUser(
          "The property with the id %d does not belong to the logged in user and can therefore not be update".formatted(
              id));
    }
    return id;
  }
}
