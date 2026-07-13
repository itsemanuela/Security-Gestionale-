package emanuela.carrubba.viaggi.services;

import emanuela.carrubba.viaggi.dto.PrenotazioneDto;
import emanuela.carrubba.viaggi.entities.Dipendente;
import emanuela.carrubba.viaggi.entities.Prenotazione;
import emanuela.carrubba.viaggi.entities.Viaggi;
import emanuela.carrubba.viaggi.exceptions.NotFoundException;
import emanuela.carrubba.viaggi.exceptions.PrenotazioneEsistente;
import emanuela.carrubba.viaggi.repositories.DipendenteRepository;
import emanuela.carrubba.viaggi.repositories.PrenotazioneRepository;
import emanuela.carrubba.viaggi.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggiRepository viaggiRepository;

    public Prenotazione effettuaPrenotazioneViaggio(PrenotazioneDto richiesta) {

        // verifico se il dipendente ha già una prenotazione in quella data
        boolean esisteGia = prenotazioneRepository.existsByDipendenteIdAndDataRichiesta(
                richiesta.dipendenteId(),
                richiesta.dataRichiesta()
        );

        if (esisteGia) {
            throw new PrenotazioneEsistente("Prenotazione esistente!");
        }
        //verifica incrociata, se esiste il dipendente associato a quel viaggio
        if (prenotazioneRepository.existsByDipendente_IdAndViaggi_Id(richiesta.dipendenteId(), richiesta.viaggioId())) {
            throw new PrenotazioneEsistente("Prenotazione già effettuata!");
        }

        Dipendente dipendente = dipendenteRepository.findById(richiesta.dipendenteId())
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato con ID: " + richiesta.dipendenteId()));

        Viaggi viaggio = viaggiRepository.findById(richiesta.viaggioId())
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato con ID: " + richiesta.viaggioId()));

        //controllo data
        Viaggi viaggiCercati = viaggiRepository.findById(richiesta.viaggioId())
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

// VERIFICA: La data del viaggio deve essere oggi o nel futuro
        if (viaggiCercati.getData().isBefore(LocalDate.now())) {
            throw new NotFoundException("Errore: non è possibile prenotare un viaggio già concluso!");
        }

        // creo prenotazione
        //controllo incrociato, vedo prima se esiste l'id del dipendente associato all'id del viaggio
        //scelto, solo dopo prenoto
        Prenotazione nuovaPrenotazione = new Prenotazione();
        nuovaPrenotazione.setDipendente(dipendente);
        nuovaPrenotazione.setViaggi(viaggio);
        nuovaPrenotazione.setDataRichiesta(richiesta.dataRichiesta());
        nuovaPrenotazione.setNote(richiesta.note());

        // salvo
        return prenotazioneRepository.save(nuovaPrenotazione);
    }

    // cserco una prenotazione tramite ID
    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione con ID " + id + " non trovata"));
    }

    public Page<Prenotazione> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneRepository.findAll(pageable);
    }

    //elimino prenotazione tramite id
    public void findByIdAndDelete(Long id) {
        Prenotazione found = this.findById(id);
        prenotazioneRepository.delete(found);
    }
    }

