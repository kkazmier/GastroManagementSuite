package pl.gastro.gastro_management_suite.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gastro.gastro_management_suite.dto.EmployeeDto;
import pl.gastro.gastro_management_suite.model.Employee;
import pl.gastro.gastro_management_suite.repository.EmployeeRepository;
import pl.gastro.gastro_management_suite.util.EmployeeMapper;
import pl.gastro.gastro_management_suite.util.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto findById(Long id) {
        Employee e = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return mapper.toDto(e);
    }

    @Override
    public EmployeeDto create(EmployeeDto dto) {
        Employee e = mapper.toEntity(dto);
        Employee saved = repository.save(e);
        return mapper.toDto(saved);
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto dto) {
        Employee existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        Employee toSave = mapper.toEntity(dto);
        toSave.setId(existing.getId());
        Employee updated = repository.save(toSave);
        return mapper.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Not found");
        }
        repository.deleteById(id);
    }
}
