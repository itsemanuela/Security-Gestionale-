package emanuela.carrubba.viaggi.repositories;

import emanuela.carrubba.viaggi.entities.Viaggi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggiRepository extends JpaRepository<Viaggi, Long> {
}
