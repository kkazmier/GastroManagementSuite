package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MenuItem extends BaseEntity {
    private String name;
    private BigDecimal price;
    private String description;
    @Enumerated(EnumType.STRING)
    private MenuCategory category;

}
