package zandb.software.swipeit.data.image;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import zandb.software.swipeit.data.property.Property;

@Entity
public class PropertyImage {

  @Id
  @GeneratedValue
  private long id;

  private String path;

  @ManyToOne
  @JoinColumn(name = "property")
  private Property property;
}
