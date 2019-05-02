package apartment.ejar.repositories;

import apartment.ejar.entities.BrokerRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrokerRolesRepository extends JpaRepository<BrokerRoles, Integer> {

    List<BrokerRoles> findAllByBrokerId(Integer brokerId);
}
