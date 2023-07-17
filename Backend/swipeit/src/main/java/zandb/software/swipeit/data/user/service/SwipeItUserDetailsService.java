package zandb.software.swipeit.data.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zandb.software.swipeit.data.user.SwipeItUser;
import zandb.software.swipeit.data.user.details.SwipeItUserDetails;
import zandb.software.swipeit.data.user.repository.SwipeItUserRepository;

@Service
public class SwipeItUserDetailsService implements UserDetailsService {

    @Autowired
    private SwipeItUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SwipeItUser user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new SwipeItUserDetails(user);
    }

    public long getIdOfLoggedInUser() {
        return ((SwipeItUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    }

    public String getUsernameOfLoggedInuser() {
        return ((SwipeItUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public String getTypeOfLoggedInUser() {
        return ((SwipeItUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserType();
    }
}
