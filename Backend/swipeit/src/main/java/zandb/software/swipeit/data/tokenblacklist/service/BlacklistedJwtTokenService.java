package zandb.software.swipeit.data.tokenblacklist.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zandb.software.swipeit.data.tokenblacklist.BlacklistedJwtToken;
import zandb.software.swipeit.data.tokenblacklist.repository.BlacklistedJwtTokenRepository;
import zandb.software.swipeit.security.JwtTokenUtil;

@Service
public class BlacklistedJwtTokenService {

  @Autowired
  private BlacklistedJwtTokenRepository blacklistedJwtTokenRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  public void logoutUser(String jwtToken) {
    BlacklistedJwtToken blacklistedJwtToken = new BlacklistedJwtToken();
    blacklistedJwtToken.setToken(jwtToken);
    blacklistedJwtToken.setExpirationDate(jwtTokenUtil.getExpirationDateFromToken(jwtToken));

    blacklistedJwtTokenRepository.save(blacklistedJwtToken);
  }

  @Scheduled(fixedRate = 60 * 60 * 24 * 1000)
  public void removeExpiredTokens() {
    blacklistedJwtTokenRepository.removeByExpirationDateAfter(new Date());
  }

  public boolean isTokenBlacklisted(String token) {
    return blacklistedJwtTokenRepository.existsByToken(token);
  }
}
