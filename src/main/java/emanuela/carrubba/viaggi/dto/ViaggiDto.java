package emanuela.carrubba.viaggi.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ViaggiDto(
        @NotBlank(message = "La destinazione è obbligatoria")
        String destinazione,

        @NotNull(message = "La data del viaggio è obbligatoria")
        @FutureOrPresent(message = "La data del viaggio deve essere oggi o nel futuro")
        LocalDate data,

        @NotBlank(message = "Lo stato del viaggio è obbligatorio")
        String stato
) {}