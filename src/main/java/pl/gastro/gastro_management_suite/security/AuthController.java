package pl.gastro.gastro_management_suite.security;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import pl.gastro.gastro_management_suite.dto.EmployeeDto;
import pl.gastro.gastro_management_suite.model.Employee;
import pl.gastro.gastro_management_suite.repository.EmployeeRepository;
import pl.gastro.gastro_management_suite.service.EmployeeService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeService employeeService;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider tokenProvider;
    private final EmployeeRepository employeeRepository;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest req) {
        Employee e = new Employee();
        e.setUsername(req.getUsername());
        e.setFullName(req.getFullName());
        System.out.println("FullName: " + req.getFullName());

        e.setEmail(req.getEmail());
        e.setPassword(passwordEncoder.encode(req.getPassword()));
        employeeRepository.save(e);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),
                            req.getPassword()
                    )
            );
            String token = tokenProvider.generateToken(req.getUsername());
            EmployeeDto userDto = employeeService.findByUsername(req.getUsername());

            return ResponseEntity.ok(new JwtAuthResponse(token, userDto));
        } catch (AuthenticationException ex) {
            return ResponseEntity
                    .status(401)
                    .body(new JwtAuthResponse.ErrorResponse("Niepoprawne dane logowania"));
        }
    }
}
