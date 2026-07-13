package emanuela.carrubba.viaggi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DipendenteDto(
        @NotBlank(message = "Lo username è obbligatorio")
        @Size(min = 3, max = 20, message = "Lo username deve essere tra 3 e 20 caratteri")
        String username,

        @NotBlank(message = "Il nome è obbligatorio")
        String nome,

        @NotBlank(message = "Il cognome è obbligatorio")
        String cognome,

        @Email(message = "L'email deve avere un formato valido")
        @NotBlank(message = "L'email è obbligatoria")
        String email,

        String avatarUrl
) {}