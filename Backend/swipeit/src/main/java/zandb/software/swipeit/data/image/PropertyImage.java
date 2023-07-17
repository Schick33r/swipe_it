package zandb.software.swipeit.data.image;

import jakarta.persistence.*;
import zandb.software.swipeit.data.property.Property;

@Entity
public class PropertyImage {

    @Id
    @GeneratedValue
    private long id;

    private String path;

    @ManyToOne
    @JoinColumn(name="property")
    private Property property;
}
