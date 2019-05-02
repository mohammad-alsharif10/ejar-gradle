package apartment.ejar.repositories;

import apartment.ejar.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Integer> {
}
