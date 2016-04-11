package hello;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Collection<Customer> findByLastName(String lastName);

    Collection<CustomerProjection> findAllProjectedBy();

    CustomerProjection findOneProjectedByFirstName(String firstName);

    Optional<CustomerProjection> findOneProjectedByLastName(String firstName);
}
