package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.gastro.gastro_management_suite.security.AuthenticationProvider;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "employee_type")
@Getter
@Setter
public class Employee extends BaseEntity {
    private String FullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthenticationProvider provider;
}
