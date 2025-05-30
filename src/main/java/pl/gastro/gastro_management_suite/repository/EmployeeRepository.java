package pl.gastro.gastro_management_suite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.gastro.gastro_management_suite.model.Employee;
import pl.gastro.gastro_management_suite.model.Role;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByFullName(String username);
    boolean existsByEmail(String email);
    Optional<Employee> findByUsername(String username);

    @Query("SELECT e.role FROM Employee e WHERE e.username = ?1")
    Role findRoleByUsername(String username);

}
