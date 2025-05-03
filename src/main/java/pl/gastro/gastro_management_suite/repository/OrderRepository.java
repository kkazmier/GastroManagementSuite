package pl.gastro.gastro_management_suite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gastro.gastro_management_suite.model.RestaurantOrder;

public interface OrderRepository extends JpaRepository<RestaurantOrder, Long> {
}
