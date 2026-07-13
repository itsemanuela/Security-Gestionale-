package emanuela.carrubba.viaggi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataRichiesta;
    private String note;

    @ManyToOne // unidirezionale verso dipendente
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    @ManyToOne // unidirezionale verso viaggi
    @JoinColumn(name = "viaggio_id")
    private Viaggi viaggi;

public Prenotazione(Long id,LocalDate dataRichiesta, String note, Dipendente dipendente, Viaggi viaggi) {
    this.id = id;
    this.dataRichiesta = dataRichiesta;
    this.note = note;
    this.dipendente = dipendente;
    this.viaggi = viaggi;

}

}