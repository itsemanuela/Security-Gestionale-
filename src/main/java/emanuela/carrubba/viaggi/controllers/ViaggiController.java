package emanuela.carrubba.viaggi.controllers;

import emanuela.carrubba.viaggi.dto.ViaggiDto;
import emanuela.carrubba.viaggi.entities.Viaggi;
import emanuela.carrubba.viaggi.services.ViaggiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {

    @Autowired
    private ViaggiService viaggiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggi creaViaggio(@RequestBody @Valid ViaggiDto body) {
        return viaggiService.save(body);
    }

    @GetMapping
    public Page<Viaggi> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return viaggiService.findAll(page, size, sortBy);
    }

    // trovo viaggio per ID
    @GetMapping("/{id}")
    public Viaggi findById(@PathVariable Long id) {
        return viaggiService.findById(id);
    }

    // aggiorna un viaggio, per cambiare lo stato o la descrizione
    @PutMapping("/{id}")
    public Viaggi update(@PathVariable Long id, @RequestBody @Validated ViaggiDto body) {
        return viaggiService.findByIdAndUpdate(id, body);
    }

    // elimino un viaggio
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        viaggiService.findByIdAndDelete(id);
    }
}