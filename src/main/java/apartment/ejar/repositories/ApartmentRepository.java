package apartment.ejar.repositories;

import apartment.ejar.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    Apartment findByAddressContaining(String address);
}
