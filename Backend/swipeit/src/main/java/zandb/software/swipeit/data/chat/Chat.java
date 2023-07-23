package zandb.software.swipeit.data.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;
import zandb.software.swipeit.data.user.SwipeItUser;

@Entity
public class Chat {

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  @JoinColumn(name = "user1")
  private SwipeItUser user1;

  @ManyToOne
  @JoinColumn(name = "user2")
  private SwipeItUser user2;

  @OneToMany(mappedBy = "chat")
  private Set<ChatLine> chatLines;


}
