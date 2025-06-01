package pl.gastro.gastro_management_suite.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import pl.gastro.gastro_management_suite.model.Role;

@Data
public class EmployeeRegistrationDto {
    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    //private Enum<Role> role;
}
