package zandb.software.swipeit.data.chat;

import jakarta.persistence.*;
import zandb.software.swipeit.data.user.SwipeItUser;

import java.time.LocalDateTime;

@Entity
public class ChatLine {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="chat")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name="chatUser")
    private SwipeItUser chatUser;

    private String text;

    private LocalDateTime timestamp;
}
