package emanuela.carrubba.viaggi.controllers;

import emanuela.carrubba.viaggi.dto.PrenotazioneDto;
import emanuela.carrubba.viaggi.entities.Prenotazione;
import emanuela.carrubba.viaggi.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    // POST: Crea una nuova prenotazione
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione create(@RequestBody @Validated PrenotazioneDto body) {
        return prenotazioneService.effettuaPrenotazioneViaggio(body);
    }

    // get per la lista di tutte le prenotazioni sempre con paginazione
    @GetMapping
    public Page<Prenotazione> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return prenotazioneService.findAll(page, size, sortBy);
    }

    // get
    @GetMapping("/{id}")
    public Prenotazione findById(@PathVariable Long id) {
        return prenotazioneService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        prenotazioneService.findByIdAndDelete(id);
    }

}