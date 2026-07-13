package emanuela.carrubba.viaggi.controllers;

import emanuela.carrubba.viaggi.dto.DipendenteDto;
import emanuela.carrubba.viaggi.entities.Dipendente;
import emanuela.carrubba.viaggi.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    // POST: creo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente crea(@RequestBody @Validated DipendenteDto body) {
        return dipendenteService.salvaDipendente(body);
    }

    // GET: lista completa con paginazione
    @GetMapping
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        return dipendenteService.findAll(page, size, sortBy);
    }

    // GET: Singolo per ID
    @GetMapping("/{id}")
    public Dipendente findById(@PathVariable Long id) {
        return dipendenteService.findById(id);
    }

    // PUT: Aggiornamento completo
    @PutMapping("/{id}")
    public Dipendente update(@PathVariable Long id, @RequestBody @Validated DipendenteDto body) {
        return dipendenteService.findByIdAndUpdate(id, body);
    }

    // PATCH: Aggiornamento parziale
    @PatchMapping("/{dipendenteId}/avatar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Dipendente updateAvatar(@PathVariable Long dipendenteId, @RequestParam("picture") MultipartFile picture) {
        return dipendenteService.uploadAvatar(dipendenteId, picture);
    }

    // DELETE: Eliminazione
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dipendenteService.findByIdAndDelete(id);
    }
}