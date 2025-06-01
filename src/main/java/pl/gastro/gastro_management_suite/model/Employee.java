package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "employee_type")
@Getter
@Setter
public class Employee extends BaseEntity {
    @Column(unique = true, nullable = false)
    @NotBlank
    private String username;

    @Column()
    private String fullName;

    @Column()
    private String email;

    @Column()
    private String phone;

    //@Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    //private Role role;


    private String password;
}
