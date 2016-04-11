package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : repository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");

            // fetch projections
            log.info("Customer found with findAllProjectedBy():");
            log.info("--------------------------------------------");
            for (CustomerProjection bauer : repository.findAllProjectedBy()) {
                log.info(bauer.getFullName());
            }
            log.info("");

            // fetch projection
            log.info("Customer found with findOneProjectedByFirstName():");
            log.info("--------------------------------------------");
            CustomerProjection projection = repository.findOneProjectedByFirstName("Kim");
            if (projection != null) {
                log.info(projection.getFullName());
            } else {
                log.info("Not Found!");
            }
            log.info("");

            // fetch optional projection
            log.info("Customer found with findOneProjectedByLastName():");
            log.info("--------------------------------------------");
            Optional<CustomerProjection> projectionOpt = repository.findOneProjectedByLastName("Dessler");
            if (projectionOpt.isPresent()) {
                log.info(projectionOpt.get().getFullName());
            } else {
                log.info("Not Found!");
            }
            log.info("");
        };
    }

}
