package pl.gastro.gastro_management_suite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    //@NotBlank
    private String fullName;
    //@Email
    //@NotBlank
    private String email;
    //@NotBlank
    private String phone;
    //@NotBlank
    private String role;
}
