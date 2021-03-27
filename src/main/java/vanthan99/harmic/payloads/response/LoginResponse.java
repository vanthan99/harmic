package vanthan99.harmic.payloads.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String email;
    private String accessToken;
    private String tokenType = "Bearer ";
    private String fullName;
    private String role;

    public LoginResponse(String username, String token, String fullName, String role){
        this.email = username;
        accessToken = token;
        this.fullName = fullName;
        this.role = role;
    }
}
