package zandb.software.swipeit.data.tokenblacklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zandb.software.swipeit.data.tokenblacklist.BlacklistedJwtToken;

import java.util.Date;
import java.util.List;

public interface BlacklistedJwtTokenRepository extends JpaRepository<BlacklistedJwtToken, Long> {

    void removeByExpirationDateAfter(Date date);

    boolean existsByToken(String token);
}
