package pl.gastro.gastro_management_suite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gastro.gastro_management_suite.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
