package emanuela.carrubba.viaggi.controllers;

import emanuela.carrubba.viaggi.dto.DipendenteDto;
import emanuela.carrubba.viaggi.dto.DipendenteResponseDTO;
import emanuela.carrubba.viaggi.dto.LoginDTO;
import emanuela.carrubba.viaggi.dto.LoginResponseDTO;
import emanuela.carrubba.viaggi.entities.Dipendente;

import emanuela.carrubba.viaggi.exceptions.ValidationException;
import emanuela.carrubba.viaggi.services.DipendenteService;
import emanuela.carrubba.viaggi.services.LoginService;
import org.springframework.http.HttpStatus;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    @PostMapping("/registrazione")
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponseDTO crea(@RequestBody @Validated DipendenteDto body, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        Dipendente dipendenteRegistrato = this.dipendenteService.salvaDipendente(body);
        return new DipendenteResponseDTO(
                dipendenteRegistrato.getEmail(),
                dipendenteRegistrato.getUsername()

        );
    }
}
