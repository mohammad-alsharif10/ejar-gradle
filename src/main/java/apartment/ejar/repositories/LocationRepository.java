package apartment.ejar.repositories;

import apartment.ejar.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


public interface LocationRepository extends JpaRepository<Location, Integer> {

    @RestResource(exported = false)
    Location findByArabicNameIn(String name);
}
