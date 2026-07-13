package emanuela.carrubba.viaggi.dto;

import java.time.LocalDateTime;

public record ErrorsDto(String message, LocalDateTime timestamp) {
}
