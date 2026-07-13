package emanuela.carrubba.viaggi.security;


import emanuela.carrubba.viaggi.entities.Dipendente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTools {
    private final String secret;

    //qui leggo il valore del token da env.properties
    public JWTools( @Value("${jwt.secret}")  String secret) {
        this.secret = secret;
    }


    // BUILDER E PARSE
    //BUILDER mi andrà a costruire il token con:
    // 1 data di emissione
    // 2 data di scadenza
    // 3 proprietario del token (id utente)
    // 4 firma del token con algoritmo hmacShaKeyFor

    public String generateToken(Dipendente  dipendente) {
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis())) // data emissione
                .expiration(new Date(System.currentTimeMillis() + 1000)) //data scadenza
                .subject(String.valueOf(dipendente.getId())) // id del proprietario
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // generatore firma
                .compact();

    }

}
