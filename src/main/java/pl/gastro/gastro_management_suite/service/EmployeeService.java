package pl.gastro.gastro_management_suite.service;

import pl.gastro.gastro_management_suite.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll();
    EmployeeDto findById(Long id);
    EmployeeDto create(EmployeeDto dto);
    EmployeeDto update(Long id, EmployeeDto dto);
    void deleteById(Long id);
}

