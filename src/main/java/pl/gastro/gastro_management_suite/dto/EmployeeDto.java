package pl.gastro.gastro_management_suite.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;

    private String username;

    private String fullName;

    private String email;

    private String phone;
}
