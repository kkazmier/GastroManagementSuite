package pl.gastro.gastro_management_suite.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gastro.gastro_management_suite.model.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
    private String token;
    private Object user;
    private Role role;


    public static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
    }
}
