package pl.gastro.gastro_management_suite.security;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gastro.gastro_management_suite.dto.EmployeeRegistrationDto;
import pl.gastro.gastro_management_suite.mapper.EmployeeMapper;
import pl.gastro.gastro_management_suite.model.Employee;
import pl.gastro.gastro_management_suite.repository.EmployeeRepository;

@Service
@AllArgsConstructor
public class AuthService {
    private final EmployeeRepository employeeRepo;
    private final PasswordEncoder passwordEncoder;

    public void register(EmployeeRegistrationDto dto) {
        if (employeeRepo.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email in use");
        }
        Employee user = new EmployeeMapper().toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        employeeRepo.save(user);
    }
}

