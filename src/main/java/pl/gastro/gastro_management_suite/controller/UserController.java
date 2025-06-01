package pl.gastro.gastro_management_suite.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gastro.gastro_management_suite.dto.EmployeeDto;
import pl.gastro.gastro_management_suite.service.EmployeeService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final EmployeeService service;

    @GetMapping("/users/me")
    public ResponseEntity<EmployeeDto> currentUser(Authentication authentication) {
        String username = authentication.getName();
        EmployeeDto dto = service.findByUsername(username);
        return ResponseEntity.ok(dto);
    }
}
