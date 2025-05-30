package pl.gastro.gastro_management_suite.security;

import lombok.Getter;
import lombok.Setter;
import pl.gastro.gastro_management_suite.model.Role;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Role role;
}
