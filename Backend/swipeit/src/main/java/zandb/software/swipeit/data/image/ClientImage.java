package zandb.software.swipeit.data.image;

import jakarta.persistence.*;
import zandb.software.swipeit.data.user.Client;

@Entity
public class ClientImage {

    @Id
    @GeneratedValue
    private long id;

    private String path;

    @ManyToOne
    @JoinColumn(name="client")
    private Client client;
}
