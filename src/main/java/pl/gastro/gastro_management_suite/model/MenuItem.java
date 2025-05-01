package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MenuItem extends BaseEntity {
    private BigDecimal price;
    private String description;
}
