package zandb.software.swipeit.data.user.dto;

import java.util.Set;
import zandb.software.swipeit.data.property.dto.PropertyDTO;

public class SupplierDTO extends SwipeItUserDTO {

  private Set<PropertyDTO> properties;

  public Set<PropertyDTO> getProperties() {
    return properties;
  }

  public void setProperties(Set<PropertyDTO> properties) {
    this.properties = properties;
  }
}
