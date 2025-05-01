package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "employee_type")
@Getter
@Setter
public abstract class Employee extends BaseEntity {
    private String FullName;
    private String email;
    private String phone;
}