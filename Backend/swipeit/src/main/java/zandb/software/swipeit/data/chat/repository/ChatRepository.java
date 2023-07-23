package zandb.software.swipeit.data.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zandb.software.swipeit.data.chat.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

}
