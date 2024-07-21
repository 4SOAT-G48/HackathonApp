package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.rest;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.PacienteNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IPacienteService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Paciente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Paciente", description = "Endpoints destinado ao cadastro de pacientes")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Operation(summary = "Cadastra um novo paciente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.cadastrarPaciente(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }

    @Operation(summary = "Atualiza um paciente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado", content = @Content),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable UUID id, @RequestBody Paciente paciente) {
        try {
            Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente.getNome(), paciente.getEmail(), paciente.getCpf());
            return ResponseEntity.ok(pacienteAtualizado);
        } catch (PacienteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Exclui um paciente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente excluído com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable UUID id) {
        try {
            pacienteService.excluirPaciente(id);
            return ResponseEntity.ok().build();
        } catch (PacienteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca um paciente pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable UUID id) {
        try {
            Paciente paciente = pacienteService.buscarPaciente(id);
            return ResponseEntity.ok(paciente);
        } catch (PacienteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca um paciente pelo email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado", content = @Content)
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@PathVariable String email) {
        try {
            Paciente paciente = pacienteService.buscarPacientePorEmail(email);
            return ResponseEntity.ok(paciente);
        } catch (PacienteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca um paciente pelo CPF")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado", content = @Content)
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Paciente> buscarPacientePorCpf(@PathVariable String cpf) {
        try {
            Paciente paciente = pacienteService.buscarPacientePorCpf(cpf);
            return ResponseEntity.ok(paciente);
        } catch (PacienteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}