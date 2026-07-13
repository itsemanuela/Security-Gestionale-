package emanuela.carrubba.viaggi.repositories;

import emanuela.carrubba.viaggi.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    boolean existsByDipendenteIdAndDataRichiesta(Long dipendenteId, LocalDate data);
    boolean existsByDipendente_IdAndViaggi_Id(Long dipendenteId, Long viaggioId);
    boolean existsByDipendenteId(Long dipendenteId);
}