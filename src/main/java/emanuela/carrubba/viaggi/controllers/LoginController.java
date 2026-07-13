package emanuela.carrubba.viaggi.controllers;

import emanuela.carrubba.viaggi.dto.LoginDTO;
import emanuela.carrubba.viaggi.dto.LoginResponseDTO;
import emanuela.carrubba.viaggi.services.DipendenteService;
import emanuela.carrubba.viaggi.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;
    private final DipendenteService dipendenteService;

    public LoginController(LoginService loginService, DipendenteService dipendenteService) {
        this.loginService = loginService;
        this.dipendenteService = dipendenteService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO) {
        String token= this.loginService.verificoCredenzialieGeneroToken(loginDTO);
        return new LoginResponseDTO(token, loginDTO.username());
    }
}
