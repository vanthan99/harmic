package vanthan99.harmic.payloads.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequest {
    @NotBlank
    private String email;

    @NotNull
    @Size(min = 4, max = 16)
    private String password;

    @NotNull
    private String fullName;

    @Size(min = 9, max = 11)
    private String phoneNumber;
}
