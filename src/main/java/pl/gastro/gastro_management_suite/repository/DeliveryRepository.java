package pl.gastro.gastro_management_suite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gastro.gastro_management_suite.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
