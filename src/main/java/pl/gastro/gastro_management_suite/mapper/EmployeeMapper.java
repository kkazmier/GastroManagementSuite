package pl.gastro.gastro_management_suite.mapper;

import pl.gastro.gastro_management_suite.model.Role;
import pl.gastro.gastro_management_suite.security.EmployeeRegistrationDto;
import pl.gastro.gastro_management_suite.model.Employee;

public class EmployeeMapper {
    public Employee toEntity(EmployeeRegistrationDto dto) {
        Employee e = new Employee();
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setPhone(dto.getPhone());
        e.setPassword(dto.getPassword());
        e.setRole((Role) dto.getRole());
        return e;
    }
    //public EmployeeDto toDto(Employee e) { /* ... */ }
}
