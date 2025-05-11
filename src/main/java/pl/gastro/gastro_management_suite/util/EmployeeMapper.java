package pl.gastro.gastro_management_suite.util;

import org.springframework.stereotype.Component;
import pl.gastro.gastro_management_suite.dto.EmployeeDto;
import pl.gastro.gastro_management_suite.model.Employee;
import pl.gastro.gastro_management_suite.model.Role;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDto dto) {
        if (dto == null) return null;
        Employee e = new Employee();
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setPhone(dto.getPhone());
        e.setRole(Role.valueOf(dto.getRole()));
        return e;
    }

    public EmployeeDto toDto(Employee entity) {
        if (entity == null) return null;
        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setRole(entity.getRole().name());
        return dto;
    }
}
