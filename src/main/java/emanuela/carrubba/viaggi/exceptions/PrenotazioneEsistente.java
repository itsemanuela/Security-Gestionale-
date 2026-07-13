package emanuela.carrubba.viaggi.exceptions;

public class PrenotazioneEsistente extends RuntimeException {
  public PrenotazioneEsistente(String message) {
    super("Il dipendente ha già una prenotazione per questa data!");
  }
}
