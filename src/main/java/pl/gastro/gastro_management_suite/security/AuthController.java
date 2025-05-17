package pl.gastro.gastro_management_suite.security;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gastro.gastro_management_suite.dto.AuthRequest;
import pl.gastro.gastro_management_suite.dto.AuthResponse;
import pl.gastro.gastro_management_suite.dto.EmployeeRegistrationDto;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid EmployeeRegistrationDto dto) {
        authService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        String token = tokenProvider.generateToken(auth.getName());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
