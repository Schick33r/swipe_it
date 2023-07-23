package zandb.software.swipeit.data.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Set;
import zandb.software.swipeit.data.property.Property;

@Entity
@DiscriminatorValue("SUPPLIER")
public class Supplier extends SwipeItUser {

  @OneToMany(mappedBy = "owner")
  private Set<Property> properties;

  public Set<Property> getProperties() {
    return properties;
  }

  public void setProperties(Set<Property> properties) {
    this.properties = properties;
  }

}
