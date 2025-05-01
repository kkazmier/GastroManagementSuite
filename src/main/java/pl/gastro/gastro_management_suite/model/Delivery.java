package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery extends Order {
    @OneToOne
    private Address address;

    @ManyToOne
    Supplier supplier;
}
