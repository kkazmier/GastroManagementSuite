package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "employee_type")
@Getter
@Setter
public class Employee extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    //@Enumerated(EnumType.STRING)
    //private Role role;

    private String password;
}
