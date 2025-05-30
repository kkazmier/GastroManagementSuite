package pl.gastro.gastro_management_suite.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gastro.gastro_management_suite.dto.EmployeeDto;
import pl.gastro.gastro_management_suite.model.Employee;
import pl.gastro.gastro_management_suite.repository.EmployeeRepository;
import pl.gastro.gastro_management_suite.security.RegisterRequest;
import pl.gastro.gastro_management_suite.util.EmployeeMapper;
import pl.gastro.gastro_management_suite.util.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto findByUsername(String username) {
        return employeeRepository.findByUsername(username)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono użytkownika o nazwie: " + username));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto findById(Long id) {
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono pracownika o ID: " + id));
        return employeeMapper.toDto(e);
    }

    @Override
    public EmployeeDto create(EmployeeDto dto) {
        Employee e = employeeMapper.toEntity(dto);
        Employee saved = employeeRepository.save(e);
        return employeeMapper.toDto(saved);
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono pracownika o ID: " + id));
        Employee toSave = employeeMapper.toEntity(dto);
        toSave.setId(existing.getId());
        Employee updated = employeeRepository.save(toSave);
        return employeeMapper.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nie znaleziono pracownika o ID: " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Transactional
    public EmployeeDto register(RegisterRequest req) {
        if (employeeRepository.existsByFullName(req.getUsername())) {
            throw new IllegalArgumentException("Username już istnieje: " + req.getUsername());
        }
        if (employeeRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email już istnieje: " + req.getEmail());
        }

        Employee employee = new Employee();
        employee.setFullName(req.getUsername());
        employee.setUsername(req.getUsername());
        employee.setPassword(passwordEncoder.encode(req.getPassword()));
        employee.setEmail(req.getEmail());
        employee.setRole(req.getRole());

        Employee saved = employeeRepository.save(employee);

        return employeeMapper.toDto(saved);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Employee e = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie istnieje: " + username));

        return User.builder()
                .username(e.getUsername())
                .password(e.getPassword())
                .authorities("ROLE_" + e.getRole())
                .build();
    }
}
