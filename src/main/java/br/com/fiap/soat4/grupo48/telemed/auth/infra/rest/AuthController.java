package br.com.fiap.soat4.grupo48.telemed.auth.infra.rest;

import br.com.fiap.soat4.grupo48.telemed.auth.application.service.AuthService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autenticacao", description = "Endpoints destinado a autenticacao dos usuarios.")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Cria um usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "400", description = "Erros na chamada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/register")
    public String registrarUsuario(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam(required = false) String cpf) {
        authService.registrarUsuario(username, password, email, cpf);
        return "Usuario cadastrado com sucesso";
    }

    @Operation(summary = "Login de um admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login realizado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(name = "token"))}),
            @ApiResponse(responseCode = "400", description = "Erros na chamada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/login/admin")
    public String loginAdmin(@RequestParam String username, @RequestParam String password) {
        return authService.loginUsuario(username, password).getAuthenticationResult().getIdToken();
    }

    @Operation(summary = "Login de um medico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login realizado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(name = "token"))}),
            @ApiResponse(responseCode = "400", description = "Erros na chamada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/login/medico")
    public String loginMedico(@RequestParam String crm, @RequestParam String password) {
        return authService.loginMedico(crm, password).getAuthenticationResult().getIdToken();
    }

    @Operation(summary = "Login de um paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login realizado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(name = "token"))}),
            @ApiResponse(responseCode = "400", description = "Erros na chamada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/login/paciente")
    public String loginPaciente(@RequestParam String email, @RequestParam String cpf, @RequestParam String password) {
        return authService.loginPaciente(email, cpf, password).getAuthenticationResult().getIdToken();
    }
}
