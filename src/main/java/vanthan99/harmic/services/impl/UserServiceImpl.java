package vanthan99.harmic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vanthan99.harmic.converters.UserConverter;
import vanthan99.harmic.payloads.request.UserRequest;
import vanthan99.harmic.payloads.response.MessageResponse;
import vanthan99.harmic.repositories.UserRepository;
import vanthan99.harmic.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserConverter converter;

    @Override
    public MessageResponse createNewAccountMember(UserRequest userRequest) {
        if (userRepository.existsById(userRequest.getEmail())){
            return new MessageResponse("Tên đăng nhập đã tồn tại! Vui lòng nhập lại tên khác");
        }
        userRepository.save(converter.toEntity(userRequest,false));
        return new MessageResponse("Tạo user thành công!");
    }


}
