package br.com.fiap.soat4.grupo48.telemed.auth.infra.rest;

import br.com.fiap.soat4.grupo48.telemed.auth.application.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autenticacao", description = "Endpoints destinado a autenticacao dos usuarios.")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String registrarUsuario(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam(required = false) String cpf) {
        authService.registrarUsuario(username, password, email, cpf);
        return "Usuario cadastrado com sucesso";
    }

    @PostMapping("/login/admin")
    public String loginAdmin(@RequestParam String username, @RequestParam String password) {
        return authService.loginUsuario(username, password).getAuthenticationResult().getIdToken();
    }

    @PostMapping("/login/medico")
    public String loginMedico(@RequestParam String crm, @RequestParam String password) {
        return authService.loginMedico(crm, password).getAuthenticationResult().getIdToken();
    }

    @PostMapping("/login/paciente")
    public String loginPaciente(@RequestParam String email, @RequestParam String cpf, @RequestParam String password) {
        return authService.loginPaciente(email, cpf, password).getAuthenticationResult().getIdToken();
    }
}
