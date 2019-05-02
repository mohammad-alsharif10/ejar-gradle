package apartment.ejar.repositories;

import apartment.ejar.entities.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrokerRepository extends JpaRepository<Broker, Integer> {


    Optional<Broker> findByUsername(String username);
}
