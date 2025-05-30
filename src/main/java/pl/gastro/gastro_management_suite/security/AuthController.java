package pl.gastro.gastro_management_suite.security;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gastro.gastro_management_suite.dto.*;
import pl.gastro.gastro_management_suite.model.Employee;
import pl.gastro.gastro_management_suite.model.Role;
import pl.gastro.gastro_management_suite.repository.EmployeeRepository;
import pl.gastro.gastro_management_suite.service.EmployeeService;
import pl.gastro.gastro_management_suite.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeServiceImpl employeeService;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider tokenProvider;
    private final EmployeeRepository employeeRepository;

    @PostMapping("/register")
    public ResponseEntity<EmployeeDto> register(@Valid @RequestBody RegisterRequest req) {
        Employee e = new Employee();
        e.setUsername(req.getUsername());
        e.setFullName(req.getFullName());
        e.setEmail(req.getEmail());
        e.setRole(Role.CHEF);

        String plain = req.getPassword();
        System.out.println("Rejestruję użytkownika" + req.getUsername() +" z haslem: " +plain);

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
            // Generujemy token JWT na podstawie zalogowanego użytkownika
            String token = tokenProvider.generateToken(String.valueOf(auth));
            // Opcjonalnie zwróć też dane użytkownika:
            EmployeeDto userDto = employeeService.findByUsername(req.getUsername());
            //Role role = (Role) userDto.getRole();
            Role role = Role.CHEF;
            System.out.println("Ustawiona rola: " + role);



            return ResponseEntity.ok(new JwtAuthResponse(token, userDto, role));
        } catch (AuthenticationException ex) {
            // 401 Unauthorized, hasło lub użytkownik niepoprawne
            return ResponseEntity
                    .status(401)
                    .body(new JwtAuthResponse.ErrorResponse("Niepoprawne dane logowania"));
        }
    }
}
