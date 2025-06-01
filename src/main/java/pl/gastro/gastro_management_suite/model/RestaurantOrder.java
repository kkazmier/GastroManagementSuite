package pl.gastro.gastro_management_suite.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RestaurantOrder extends BaseEntity {
    @ManyToMany
    private List<MenuItem> items;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal totalAmount = BigDecimal.ZERO;

    public void recalculateTotal() {
        totalAmount = items.stream()
                .map(MenuItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
