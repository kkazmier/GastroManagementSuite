package pl.gastro.gastro_management_suite.service;

import jakarta.validation.constraints.NotBlank;
import pl.gastro.gastro_management_suite.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll();
    EmployeeDto findById(Long id);
    EmployeeDto create(EmployeeDto dto);
    EmployeeDto update(Long id, EmployeeDto dto);
    void deleteById(Long id);

    EmployeeDto findByUsername(@NotBlank String username);
}

