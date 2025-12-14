package by.vadarod.E_Library.tools.security;

import by.vadarod.E_Library.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userService.getUserDetails(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("Пользовотель с логином " + username + "не найден");
        }

        return userDetails;
    }
}
