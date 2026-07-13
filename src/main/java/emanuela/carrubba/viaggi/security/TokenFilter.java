package emanuela.carrubba.viaggi.security;

import emanuela.carrubba.viaggi.exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final JWTools jwt;

    public TokenFilter(JWTools jwt) {
        this.jwt = jwt;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // questo metodo serve ad applicate dei filtri, per controllare passo passo se i token sono a posto prima di arrivare
        //al controller. Vengono invocati ad ogni richiesta, cioè per ogni request il filtro viene riattivato per il controllo
        // della catena di montaggio.

        //innanzitutto verifico l'integrità del token, cioè deve iniziare con Bearer

        String headerToken = request.getHeader("Authorization");
        if (headerToken == null && !headerToken.startsWith("Bearer "))
            throw new UnauthorizedException("Il formato del token è errato, inserisci formato Bearer!");

        // ora estraggo il token dall'header con replace
        String tokenParziale= headerToken.substring(7);
        System.out.println("token parziale: " + tokenParziale);

//ora, una volta estratto posso verificare se è integro
this.jwt.validateToken(tokenParziale);
// se è a posto uso metodo della libreria per andar avanti
        filterChain.doFilter(request, response);
    }

    //per specificare su quali filtri devo o non devo intervenire uso metodo shouldNotFilter


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
        // gli endpoint con auth non hanno protezioni
    }
}
