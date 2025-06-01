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
import pl.gastro.gastro_management_suite.mapper.EmployeeMapper;
import pl.gastro.gastro_management_suite.model.Employee;
import pl.gastro.gastro_management_suite.repository.EmployeeRepository;
import pl.gastro.gastro_management_suite.security.EmployeeRegistrationDto;
import pl.gastro.gastro_management_suite.security.RegisterRequest;
import pl.gastro.gastro_management_suite.util.ResourceNotFoundException;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

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
            throw new IllegalArgumentException("Nazwa użytkownika już istnieje: " + req.getUsername());
        }
        if (employeeRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email już istnieje: " + req.getEmail());
        }

        // Tworzenie encji z DTO
        Employee employee = employeeMapper.toEntity(new EmployeeRegistrationDto(

        ));

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toDto(saved);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee e = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie istnieje: " + username));

        return User.builder()
                .username(e.getUsername())
                .password(e.getPassword())
                .authorities("ROLE_USER") // lub: "ROLE_" + e.getRole().name()
                .build();
    }

    @Override
    public EmployeeDto findByUsername(@NotBlank String username) {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono użytkownika o nazwie: " + username));
        return employeeMapper.toDto(employee);
    }

}
