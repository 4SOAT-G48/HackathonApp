package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.rest;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IAdminService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Admin;
import br.com.fiap.soat4.grupo48.telemed.commons.exception.ApplicationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Admin", description = "Endpoints destinado ao cadastro de administradores")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "Cria um admin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Admin Criado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))}),
        @ApiResponse(responseCode = "400", description = "Admin inválido", content = {@Content}),
    })
    @PostMapping
    public ResponseEntity<Admin> criarAdmin(@RequestBody AdminDTO admin) {
        Admin adminCriado = adminService.criarAdmin(admin.getNome(), admin.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(adminCriado);
    }

    @Operation(summary = "Atualiza um admin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Admin Atualizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))}),
        @ApiResponse(responseCode = "400", description = "Admin inválido", content = {@Content}),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Admin> atualizarAdmin(@PathVariable UUID id, @RequestBody AdminDTO adminDTO) {
        try {
            Admin adminAtualizado = adminService.atualizarAdmin(id, adminDTO.getNome(), adminDTO.getEmail());
            return ResponseEntity.ok(adminAtualizado);
        } catch (ApplicationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Deleta um admin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Admin deletado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))}),
        @ApiResponse(responseCode = "404", description = "Admin não encontrado", content = {@Content}),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdmin(@PathVariable UUID id) {
        try {
            adminService.deletarAdmin(id);
            return ResponseEntity.ok().build();
        } catch (ApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Recupera todos os admins")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Admin encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))}),
        @ApiResponse(responseCode = "404", description = "Admin não encontrado", content = {@Content}),
    })
    @GetMapping
    public ResponseEntity<List<Admin>> buscarTodosAdmins() {
        List<Admin> admins = adminService.buscarTodosAdmins();
        if (admins.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(admins);
    }

    @Operation(summary = "Recupera um admin pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Admin encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))}),
        @ApiResponse(responseCode = "404", description = "Admin não encontrado", content = {@Content}),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAdmin(@PathVariable UUID id) {
        Admin admin = null;
        try {
            admin = adminService.buscarAdmin(id);
        } catch (ApplicationException e) { //todas as exceções da aplicação/negócio
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(admin);
    }

    @Operation(summary = "Atualiza um admin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Admin Atualizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))}),
        @ApiResponse(responseCode = "400", description = "Admin inválido", content = {@Content}),
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarAdminPeloEmail(@PathVariable String email) {
        Admin admin = null;
        try {
            admin = adminService.buscarAdminPorEmail(email);
        } catch (ApplicationException e) { //todas as exceções da aplicação/negócio
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(admin);
    }


}
