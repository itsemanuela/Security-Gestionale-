package emanuela.carrubba.viaggi.services;


import emanuela.carrubba.viaggi.dto.LoginDTO;
import emanuela.carrubba.viaggi.entities.Dipendente;
import emanuela.carrubba.viaggi.exceptions.UnauthorizedException;
import emanuela.carrubba.viaggi.security.JWTools;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private DipendenteService dipendenteService;
    private final JWTools jwTools;
    public LoginService(DipendenteService dipendenteService, JWTools jwTools) {
        this.dipendenteService = new DipendenteService();
        this.jwTools = jwTools;
    }

    public String verificoCredenzialieGeneroToken(LoginDTO loginDTO) {
        //controllo se esiste dipendente con qauella email
        Dipendente trovato= dipendenteService.findByEmail(loginDTO.email());
       // controllo se username corrisponde
        if(trovato.getUsername().equals(loginDTO.username())){
            return this.jwTools.generateToken(trovato);

        } else throw new UnauthorizedException("Credenziale non valide");
    }
}
