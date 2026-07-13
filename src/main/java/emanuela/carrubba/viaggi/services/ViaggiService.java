package emanuela.carrubba.viaggi.services;


import emanuela.carrubba.viaggi.StatoViaggio;
import emanuela.carrubba.viaggi.dto.ViaggiDto;
import emanuela.carrubba.viaggi.entities.Viaggi;
import emanuela.carrubba.viaggi.exceptions.NotFoundException;
import emanuela.carrubba.viaggi.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggiService {
    @Autowired
    private ViaggiRepository viaggiRepository;

    public Viaggi save(ViaggiDto body) {
        Viaggi nuovoViaggio = new Viaggi();
        nuovoViaggio.setDestinazione(body.destinazione());
        nuovoViaggio.setData(body.data());
        nuovoViaggio.setDestinazione(body.destinazione());
        nuovoViaggio.setStato(StatoViaggio.IN_PROGRAMMA); // Stato di default

        return viaggiRepository.save(nuovoViaggio);
    }

    public Viaggi findById(Long id) {
        return viaggiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));
    }

     //paginazione della lista dei viaggi
     public Page<Viaggi> findAll(int page, int size, String sortBy) {
         Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
         return viaggiRepository.findAll(pageable);}

    // metodo per aggiornare un viaggio esistente
    public Viaggi findByIdAndUpdate(Long id, ViaggiDto body) {
        Viaggi found = this.findById(id);
        found.setDestinazione(body.destinazione());
        found.setData(body.data());
        found.setDestinazione(body.destinazione());

        return viaggiRepository.save(found);
    }

    // Metodo per eliminare un viaggio
    public void findByIdAndDelete(Long id) {
        Viaggi found = this.findById(id);

        // elimino
        viaggiRepository.delete(found);
    }
}