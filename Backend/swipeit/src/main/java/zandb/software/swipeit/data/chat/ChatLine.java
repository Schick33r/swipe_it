package zandb.software.swipeit.data.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import zandb.software.swipeit.data.user.SwipeItUser;

@Entity
public class ChatLine {

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  @JoinColumn(name = "chat")
  private Chat chat;

  @ManyToOne
  @JoinColumn(name = "chatUser")
  private SwipeItUser chatUser;

  private String text;

  private LocalDateTime timestamp;
}
