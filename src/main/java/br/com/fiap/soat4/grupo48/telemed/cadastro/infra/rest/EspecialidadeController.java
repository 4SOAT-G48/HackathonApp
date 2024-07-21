package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.rest;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.EspecialidadeNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IEspecialidadeService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;
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

@Tag(name = "Especialidade", description = "Endpoints destinado ao cadastro de especialidades")
@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {
    private final IEspecialidadeService especialidadeService;

    public EspecialidadeController(IEspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @Operation(summary = "Cria uma especialidade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Especialidade criada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Especialidade.class))}),
        @ApiResponse(responseCode = "400", description = "Especialidade inválida", content = {@Content}),
    })
    @PostMapping
    public ResponseEntity<Especialidade> criarEspecialidade(@RequestBody EspecialidadeDTO especialidadeDTO) {
        Especialidade especialidadeCriada = especialidadeService.criarEspecialidade(especialidadeDTO.getCodigo(), especialidadeDTO.getDescricao());
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadeCriada);
    }

    @Operation(summary = "Atualiza uma especialidade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Especialidade atualizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Especialidade.class))}),
        @ApiResponse(responseCode = "400", description = "Especialidade inválida", content = {@Content}),
        @ApiResponse(responseCode = "404", description = "Especialidade não encontrada", content = {@Content}),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> atualizarEspecialidade(@PathVariable UUID id, @RequestBody EspecialidadeDTO especialidadeDTO) {
        try {
            Especialidade especialidadeAtualizada = especialidadeService.atualizarEspecialidade(id, especialidadeDTO.getCodigo(), especialidadeDTO.getDescricao());
            return ResponseEntity.ok(especialidadeAtualizada);
        } catch (EspecialidadeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Deleta uma especialidade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Especialidade deletada", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "Especialidade não encontrada", content = {@Content}),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEspecialidade(@PathVariable UUID id) {
        try {
            especialidadeService.deletarEspecialidade(id);
            return ResponseEntity.ok().build();
        } catch (ApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca todas as especialidades")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Especialidades encontradas", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Especialidade.class))}),
        @ApiResponse(responseCode = "204", description = "Nenhuma especialidade encontrada", content = {@Content}),
    })
    @GetMapping
    public ResponseEntity<List<Especialidade>> buscarTodasEspecialidades() {
        List<Especialidade> especialidades = especialidadeService.buscarTodasEspecialidades();
        if (especialidades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(especialidades);
    }

    @Operation(summary = "Busca uma especialidade pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Especialidade encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Especialidade.class))}),
        @ApiResponse(responseCode = "404", description = "Especialidade não encontrada", content = {@Content}),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEspecialidade(@PathVariable UUID id) {
        Especialidade especialidade = null;
        try {
            especialidade = especialidadeService.buscarEspecialidade(id);
        } catch (EspecialidadeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(especialidade);
    }
}