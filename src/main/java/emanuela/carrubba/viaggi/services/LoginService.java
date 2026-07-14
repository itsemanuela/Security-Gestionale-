package emanuela.carrubba.viaggi.services;


import emanuela.carrubba.viaggi.dto.LoginDTO;
import emanuela.carrubba.viaggi.entities.Dipendente;
import emanuela.carrubba.viaggi.exceptions.UnauthorizedException;
import emanuela.carrubba.viaggi.security.JWTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private DipendenteService dipendenteService;
    private final JWTools jwTools;
    public LoginService(DipendenteService dipendenteService, JWTools jwTools) {
        this.dipendenteService = dipendenteService;
        this.jwTools = jwTools;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String verificoCredenzialieGeneroToken(LoginDTO loginDTO) {
        //controllo se esiste dipendente con qauella email
        Dipendente trovato= dipendenteService.findByEmail(loginDTO.email());
       // controllo se username corrisponde

        boolean passwordValida= passwordEncoder.matches(loginDTO.password(), trovato.getPassword());

        if(passwordValida){
            return this.jwTools.generateToken(trovato);

        } else throw new UnauthorizedException("Credenziali non valide");
    }
}
