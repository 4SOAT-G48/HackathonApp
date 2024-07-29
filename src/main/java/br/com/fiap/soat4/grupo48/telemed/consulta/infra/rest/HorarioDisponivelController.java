package br.com.fiap.soat4.grupo48.telemed.consulta.infra.rest;

import br.com.fiap.soat4.grupo48.telemed.commons.exception.ApplicationException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.in.IHorarioDisponivelService;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.HorarioDisponivel;
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

@Tag(name = "HorarioDisponivel", description = "Endpoints para gerenciamento de horários disponíveis")
@RestController
@RequestMapping("/horarios-disponiveis")
public class HorarioDisponivelController {
    private final IHorarioDisponivelService horarioDisponivelService;

    public HorarioDisponivelController(IHorarioDisponivelService horarioDisponivelService) {
        this.horarioDisponivelService = horarioDisponivelService;
    }

    @Operation(summary = "Cria um novo horário disponível")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Horário disponível criado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HorarioDisponivel.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<?> criarHorarioDisponivel(@RequestBody HorarioDisponivelDTO horarioDisponivel) {
        try {
            HorarioDisponivel novoHorarioDisponivel =
                horarioDisponivelService.criarHorarioDisponivel(
                    horarioDisponivel.getMedicoId(),
                    horarioDisponivel.getData(),
                    horarioDisponivel.getHoraInicio(),
                    horarioDisponivel.getHoraFim()
                );
            return ResponseEntity.status(HttpStatus.CREATED).body(novoHorarioDisponivel);
        } catch (ApplicationException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Operation(summary = "Atualiza um horário disponível")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horário disponível atualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HorarioDisponivel.class))),
        @ApiResponse(responseCode = "404", description = "Horário disponível não encontrado", content = @Content),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<HorarioDisponivel> atualizarHorarioDisponivel(@PathVariable UUID id, @RequestBody HorarioDisponivelDTO horarioDisponivel) {
        try {
            HorarioDisponivel horarioDisponivelAtualizado = horarioDisponivelService.atualizarHorarioDisponivel(id, horarioDisponivel.getData(), horarioDisponivel.getHoraInicio(), horarioDisponivel.getHoraFim());
            return ResponseEntity.ok(horarioDisponivelAtualizado);
        } catch (ApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Deleta um horário disponível pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horário disponível deletado", content = @Content),
        @ApiResponse(responseCode = "404", description = "Horário disponível não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHorarioDisponivel(@PathVariable UUID id) {
        try {
            horarioDisponivelService.deletarPorId(id);
            return ResponseEntity.ok().build();
        } catch (ApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca todos os horários disponíveis")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horários disponíveis encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HorarioDisponivel.class))),
        @ApiResponse(responseCode = "204", description = "Nenhum horário disponível encontrado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<HorarioDisponivel>> buscarTodosHorariosDisponiveis() {
        List<HorarioDisponivel> horariosDisponiveis = horarioDisponivelService.listarTodos();
        if (horariosDisponiveis.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(horariosDisponiveis);
    }

    @Operation(summary = "Busca um horário disponível pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horário disponível encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HorarioDisponivel.class))),
        @ApiResponse(responseCode = "404", description = "Horário disponível não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<HorarioDisponivel> buscarHorarioDisponivelPorId(@PathVariable UUID id) {
        try {
            HorarioDisponivel horarioDisponivel = horarioDisponivelService.buscarPorId(id);
            return ResponseEntity.ok(horarioDisponivel);
        } catch (ApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Buscar horários disponíveis por médico
    @Operation(summary = "Busca horários disponíveis por médico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horários disponíveis encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HorarioDisponivel.class))),
        @ApiResponse(responseCode = "204", description = "Nenhum horário disponível encontrado", content = @Content)
    })
    @GetMapping("/medico/{id}")
    public ResponseEntity<List<HorarioDisponivel>> buscarHorariosDisponiveisPorMedico(@PathVariable UUID id) {
        List<HorarioDisponivel> horariosDisponiveis = horarioDisponivelService.listarPorMedico(id);
        if (horariosDisponiveis.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(horariosDisponiveis);
    }
}
