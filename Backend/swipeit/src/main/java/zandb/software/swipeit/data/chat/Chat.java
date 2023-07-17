package zandb.software.swipeit.data.chat;

import jakarta.persistence.*;
import zandb.software.swipeit.data.user.SwipeItUser;

import java.util.Set;

@Entity
public class Chat {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="user1")
    private SwipeItUser user1;

    @ManyToOne
    @JoinColumn(name="user2")
    private SwipeItUser user2;

    @OneToMany(mappedBy="chat")
    private Set<ChatLine> chatLines;


}
