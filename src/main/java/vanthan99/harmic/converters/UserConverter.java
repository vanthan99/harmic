package vanthan99.harmic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vanthan99.harmic.entities.User;
import vanthan99.harmic.entities.enums.ERole;
import vanthan99.harmic.payloads.request.UserRequest;

@Component
public class UserConverter {
    @Autowired
    PasswordEncoder passwordEncoder;

    public User toEntity(UserRequest request, boolean isAdmin) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(isAdmin ? ERole.ADMIN : ERole.MEMBER);
        return user;
    }
}
