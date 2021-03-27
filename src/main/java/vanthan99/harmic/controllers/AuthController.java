package vanthan99.harmic.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vanthan99.harmic.entities.User;
import vanthan99.harmic.payloads.request.LoginRequest;
import vanthan99.harmic.payloads.request.UserRequest;
import vanthan99.harmic.payloads.response.LoginResponse;
import vanthan99.harmic.payloads.response.UserResponse;
import vanthan99.harmic.repositories.UserRepository;
import vanthan99.harmic.securities.JwtTokenProvider;
import vanthan99.harmic.securities.MyUserDetails;
import vanthan99.harmic.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Api
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    @ApiOperation(value = "Đăng nhập")
    public ResponseEntity<Object> login(
            @ApiParam(value = "Tài khoản đăng nhâp", required = true)
            @RequestBody LoginRequest loginRequest
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = jwtTokenProvider.generateToken((MyUserDetails) authentication.getPrincipal());

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

//        Set<Role> roles = userDetails.getAuthorities().stream()
//                .map(item -> roleService.findByName(ERole.valueOf(item.getAuthority())))
//                .collect(Collectors.toSet());
//        Set<String> strRoles = new HashSet<>();


//        roles.forEach(e -> {
//            strRoles.add(e.getName().toString());
//        });

//        Role role = userDetails.getAuthorities().stream().map(item -> roleRepository.findByName(item))
        return ResponseEntity.ok().body(new LoginResponse(
                userDetails.getUsername(),
                jwt,
                userDetails.getFullName(),
                userDetails.getRole()
        ));
    }


    @GetMapping("/me")
    @ApiOperation(value = "Xem thông tin chính mình")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<Object> me(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = userRepository.findById(myUserDetails.getUsername()).orElse(null);
        assert user != null;
        UserResponse userResponse = new UserResponse(
                user.getEmail(),
                user.getFullName(),
                user.getPhoneNumber()
        );

        return ResponseEntity.ok(userResponse);
    }

    // tạo tài khoản thành viên
    @PostMapping("/register")
    @ApiOperation(value = "Đăng ký tài khoản thành viên")
    public ResponseEntity<Object> createNewAccountMember(
            @RequestBody @Valid UserRequest userRequest
    ) {
        return ResponseEntity.ok(userService.createNewAccountMember(userRequest));
    }

//    // tạo tài khoản Admin
//    @PostMapping("/createAdmin")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<Object> createNewAccountAdmin(
//            @RequestBody @Valid UserRequest userRequest
//    ){
//        return ResponseEntity.ok(userService.createNewAccountAdmin(userRequest));
//    }

    // chỉnh sửa tại khoản cá nhân
//    @PostMapping("/editMe")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MEMBER')")
//    @ApiOperation(value = "Chỉnh sửa thông tin cá nhân")
//    public ResponseEntity<Object> editMe(
//            @RequestBody @Valid UserRequest userRequest
//    ) {
//        return ResponseEntity.ok(userService.updateUser(userRequest));
//    }

    // đổi mật khẩu cá nhân
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MEMBER')")
//    @PutMapping("/changePassword")
//    @ApiOperation(value = "Đổi mật khẩu")
//    public ResponseEntity<Object> changePassword(
//            @RequestBody PasswordRequest passwordRequest,
//            @AuthenticationPrincipal MyUserDetails myUserDetails
//    ) {
//        User user = userRepository.getOne(myUserDetails.getUsername());
//        return ResponseEntity.ok(userService.changePassword(user, passwordRequest));
//    }

//    @GetMapping("/changePasswordByEmail/{email}/{newPassword}")
//    @ApiOperation(value = "Đổi mật khẩu bằng email")
//    public ResponseEntity<Object> changePasswordByEmail(
//            @PathVariable String newPassword, @PathVariable String email
//    ) {
//        User user = userRepository.findByEmail(email);
//        return ResponseEntity.ok(userService.changePasswordByEmail(user, newPassword));
//    }
}
