package emanuela.carrubba.viaggi.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsDto(String message, LocalDateTime timestamp) {
}
