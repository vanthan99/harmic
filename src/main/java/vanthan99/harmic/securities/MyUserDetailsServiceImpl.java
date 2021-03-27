package vanthan99.harmic.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vanthan99.harmic.entities.User;
import vanthan99.harmic.repositories.UserRepository;

@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findById(s).orElse(null);
        if (user == null){
            throw new UsernameNotFoundException("could not find by user!");
        }
        return new MyUserDetails(user);
    }
}
