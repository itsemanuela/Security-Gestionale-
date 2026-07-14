package emanuela.carrubba.viaggi.entities;

import emanuela.carrubba.viaggi.Ruolo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dipendente implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String avatarUrl;
    private String password;
@Enumerated(EnumType.STRING)
private Ruolo ruolo;
    public Dipendente(String username, String nome, String cognome, String email, String avatarUrl, String password, String Ruolo) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.password = password;
        this.ruolo = emanuela.carrubba.viaggi.Ruolo.DIPENDENTE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }
    @Override
    public String getUsername() {
        return this.email;
    }
}