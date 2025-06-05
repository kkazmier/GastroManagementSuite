package pl.gastro.gastro_management_suite.mapper;

import org.springframework.stereotype.Component;
import pl.gastro.gastro_management_suite.dto.EmployeeDto;
import pl.gastro.gastro_management_suite.model.Employee;
import pl.gastro.gastro_management_suite.security.EmployeeRegistrationDto;

@Component
public class EmployeeMapper {

    // Konwersja z EmployeeRegistrationDto na encję Employee
    public Employee toEntity(EmployeeRegistrationDto dto) {
        if (dto == null) return null;

        Employee e = new Employee();
        e.setUsername(dto.getUsername());
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setPhone(dto.getPhone());
        e.setPassword(dto.getPassword()); // Hasło musi być zakodowane przez serwis
        return e;
    }

    // Konwersja z EmployeeDto na encję Employee
    public Employee toEntity(EmployeeDto dto) {
        if (dto == null) return null;

        Employee e = new Employee();
        e.setId(dto.getId());
        e.setUsername(dto.getUsername());
        e.setEmail(dto.getEmail());
        e.setPhone(dto.getPhone());
        return e;
    }

    public EmployeeDto toDto(Employee e) {
        if (e == null) return null;

        EmployeeDto dto = new EmployeeDto();
        dto.setId(e.getId());
        dto.setUsername(e.getUsername());
        dto.setFullName(e.getFullName());
        dto.setEmail(e.getEmail());
        dto.setPhone(e.getPhone());
        return dto;
    }


    // Konwersja z Employee na EmployeeRegistrationDto (pełne dane użytkownika)
    public EmployeeRegistrationDto toRegistrationDto(Employee e) {
        if (e == null) return null;

        EmployeeRegistrationDto dto = new EmployeeRegistrationDto();
        dto.setUsername(e.getUsername());
        dto.setFullName(e.getFullName());
        dto.setEmail(e.getEmail());
        dto.setPhone(e.getPhone());
        dto.setPassword(e.getPassword()); // Uwaga: to będzie hash! Tylko jeśli potrzebujesz!
        return dto;
    }
}
