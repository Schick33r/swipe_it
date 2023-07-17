package zandb.software.swipeit.data.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zandb.software.swipeit.data.user.SwipeItUser;

@Repository
public interface SwipeItUserRepository extends JpaRepository<SwipeItUser, Long> {

    SwipeItUser findByUsername(String username);
}
