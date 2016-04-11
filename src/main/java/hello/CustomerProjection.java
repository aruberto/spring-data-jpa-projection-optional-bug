package hello;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerProjection {

    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();
}
