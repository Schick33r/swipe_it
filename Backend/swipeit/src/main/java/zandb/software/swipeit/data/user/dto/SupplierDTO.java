package zandb.software.swipeit.data.user.dto;

import jakarta.persistence.OneToMany;
import zandb.software.swipeit.data.property.Property;
import zandb.software.swipeit.data.property.dto.PropertyDTO;

import java.util.Set;

public class SupplierDTO extends SwipeItUserDTO {

    private Set<PropertyDTO> properties;

    public Set<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(Set<PropertyDTO> properties) {
        this.properties = properties;
    }
}
