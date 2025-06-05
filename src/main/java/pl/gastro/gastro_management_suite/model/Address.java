package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address extends BaseEntity {
    private String street;
    private String city;
    private String postalCode;
    private String country;

    private Double latitude;
    private Double longitude;
}
