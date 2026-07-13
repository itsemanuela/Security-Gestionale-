package emanuela.carrubba.viaggi.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PrenotazioneDto(
        @NotNull(message = "L'ID del dipendente è obbligatorio")
        Long dipendenteId,

        @NotNull(message = "L'ID del viaggio è obbligatorio")
        Long viaggioId,

        @NotNull(message = "La data è obbligatoria")
        //per validare la data che nn sia nel passato
        @FutureOrPresent(message = "La data deve essere nel presente o nel futuro")
        LocalDate dataRichiesta,

        String note
) {}