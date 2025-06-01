package pl.gastro.gastro_management_suite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import pl.gastro.gastro_management_suite.model.Role;

@Data
public class EmployeeDto {
    private Long id;
    //@NotBlank
    private String username;

    private String fullName;
    //@Email

    private String email;

    private String phone;
    //@NotBlank
    //private Enum<Role> role;

}
