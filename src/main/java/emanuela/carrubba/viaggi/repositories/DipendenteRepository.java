package emanuela.carrubba.viaggi.repositories;

import emanuela.carrubba.viaggi.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    Optional <Dipendente> findByUsername(String username);
   Optional <Dipendente> findByEmail(String email);

}
