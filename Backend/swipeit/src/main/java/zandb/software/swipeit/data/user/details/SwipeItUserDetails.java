package zandb.software.swipeit.data.user.details;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zandb.software.swipeit.data.user.SwipeItUser;


public class SwipeItUserDetails implements UserDetails {

  private SwipeItUser user;

  public SwipeItUserDetails(SwipeItUser user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public long getUserId() {
    return user.getUserId();
  }

  public String getUserType() {
    return user.getUserType();
  }
}
