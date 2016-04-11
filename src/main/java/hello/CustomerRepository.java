package hello;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Collection<Customer> findByLastName(String lastName);

    // Collection<CustomerProjection> findAllProjected();
}
