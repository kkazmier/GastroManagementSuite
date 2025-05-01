package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CHEF")
@Getter
@Setter
public class Chef extends Employee {

}
