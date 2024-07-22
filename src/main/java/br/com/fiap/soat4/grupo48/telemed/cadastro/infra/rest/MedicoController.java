package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.rest;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.*;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IMedicoService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Tag(name = "Médico", description = "Endpoints destinado ao cadastro de médicos")
@RestController
@RequestMapping("/v1/medicos")
public class MedicoController {
    private final IMedicoService medicoService;

    public MedicoController(IMedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @Operation(summary = "Cria um médico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Médico Criado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class))}),
        @ApiResponse(responseCode = "400", description = "Médico inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @PostMapping
    public ResponseEntity<?> criarMedico(@RequestBody MedicoDTO medico) {
        Medico medicoCriado = null;
        try {
            medicoCriado = medicoService.criarMedico(medico.getNome(), medico.getEmail(), medico.getCrm());
        } catch (ApplicationException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCriado);
    }

    @Operation(summary = "Atualiza um médico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médico Atualizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class))}),
        @ApiResponse(responseCode = "400", description = "Médico inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMedico(@PathVariable UUID id, @RequestBody MedicoDTO medicoDTO) {
        try {
            Medico medicoAtualizado = medicoService.atualizarMedico(id, medicoDTO.getNome(), medicoDTO.getEmail(), medicoDTO.getCrm());
            return ResponseEntity.ok(medicoAtualizado);
        } catch (ApplicationException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Operation(summary = "Deleta um médico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médico deletado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class))}),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado", content = {@Content}),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarMedico(@PathVariable UUID id) {
        try {
            medicoService.deletarMedico(id);
        } catch (MedicoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MedicoIllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Busca um médico pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médico encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class))}),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado", content = {@Content}),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedico(@PathVariable UUID id) {
        try {
            Medico medico = medicoService.buscarMedico(id);
            return ResponseEntity.ok(medico);
        } catch (MedicoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca um médico pelo email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médico encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class))}),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado", content = {@Content}),
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<Medico> buscarMedicoPorEmail(@PathVariable String email) {
        try {
            Medico medico = medicoService.buscarMedicoPorEmail(email);
            return ResponseEntity.ok(medico);
        } catch (MedicoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca um médico pelo CRM")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médico encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class))}),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado", content = {@Content}),
    })
    @GetMapping("/crm/{crm}")
    public ResponseEntity<Medico> buscarMedicoPorCrm(@PathVariable String crm) {
        try {
            Medico medico = medicoService.buscarMedicoPorCrm(crm);
            return ResponseEntity.ok(medico);
        } catch (MedicoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca médicos por especialidade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médicos encontrados", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medico[].class))}),
        @ApiResponse(responseCode = "204", description = "Nenhum médico encontrado", content = {@Content}),
    })
    @GetMapping("/especialidade/{idEspecialidade}")
    public ResponseEntity<List<Medico>> buscarMedicosPorEspecialidade(@PathVariable UUID idEspecialidade) {
        List<Medico> medicos = medicoService.buscarMedicosPorEspecialidade(idEspecialidade);
        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicos);
    }

    @Operation(summary = "Vincula uma especialidade a um médico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Especialidade vinculada ao médico", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class))}),
        @ApiResponse(responseCode = "404", description = "Médico ou especialidade não encontrada", content = {@Content}),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = {@Content})
    })
    @PostMapping("/{idMedico}/especialidades/{idEspecialidade}")
    public ResponseEntity<?> vincularEspecialidade(@PathVariable UUID idMedico, @PathVariable UUID idEspecialidade) {
        try {
            Medico medico = medicoService.vincularEspecialidade(idMedico, idEspecialidade);
            return ResponseEntity.ok(medico);
        } catch (MedicoNotFoundException | EspecialidadeNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (EspecialidadeAlreadyLinkedException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Operation(summary = "Desvincula uma especialidade de um médico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Especialidade desvinculada do médico", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class))}),
        @ApiResponse(responseCode = "404", description = "Médico ou especialidade não encontrada", content = {@Content}),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = {@Content})
    })
    @DeleteMapping("/{idMedico}/especialidades/{idEspecialidade}")
    public ResponseEntity<?> desvincularEspecialidade(@PathVariable UUID idMedico, @PathVariable UUID idEspecialidade) {
        try {
            Medico medico = medicoService.desvincularEspecialidade(idMedico, idEspecialidade);
            return ResponseEntity.ok(medico);
        } catch (MedicoNotFoundException | EspecialidadeNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (EspecialidadeNotLinkedException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
