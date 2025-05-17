package pl.gastro.gastro_management_suite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeRegistrationDto {
    @NotBlank
    private String fullName;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String password;
}
