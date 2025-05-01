package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("SUPPLIER")
@Getter
@Setter
public class Supplier extends Employee {

}
