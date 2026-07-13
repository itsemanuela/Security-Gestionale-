package emanuela.carrubba.viaggi.exceptions;

import emanuela.carrubba.viaggi.dto.ErrorsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //  validazione del dto per inviare a postman stringa di messaggi di errori
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorsDto> handleValidationExceptions(MethodArgumentNotValidException ex) {

        //quando il validatore trova degli errori li mette in una lista, field error li estrae, lo stream trasforma
        //tutto il flusso dei dati, con map trasformo (per ogni errore creo una stringa leggibile, quindi avro
        //tutti i messaggi di default scritti nei dto validation, con collect creo una collezione di stringhe seprandole con la virgola
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                new ErrorsDto(errorMessage, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST
        );
    }

    //  errore Not Found
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorsDto> handleNotFound(NotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorsDto(ex.getMessage(), LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }

    // errore renotazione esistente
    @ExceptionHandler(PrenotazioneEsistente.class)
    public ResponseEntity<ErrorsDto> handlePrenotazioneEsistente(PrenotazioneEsistente ex) {
        return new ResponseEntity<>(
                new ErrorsDto(ex.getMessage(), LocalDateTime.now()),
                HttpStatus.CONFLICT
        );
    }

    //eliminazione dipendente con prenotazioni attive
    @ExceptionHandler(EliminazioneDipendente.class)
    public ResponseEntity<ErrorsDto>handleEliminazioneDipendente(EliminazioneDipendente ex) {
        return new ResponseEntity<>(
                new ErrorsDto(ex.getMessage(), LocalDateTime.now()),
                HttpStatus.CONFLICT
        );
    }
}