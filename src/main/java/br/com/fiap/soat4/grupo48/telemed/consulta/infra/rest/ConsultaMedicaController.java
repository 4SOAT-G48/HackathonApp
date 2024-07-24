package br.com.fiap.soat4.grupo48.telemed.consulta.infra.rest;

import br.com.fiap.soat4.grupo48.telemed.cadastro.infra.rest.ErrorResponse;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.ConsultaMedicaIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.ConsultaMedicaNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.HorarioDisponivelNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.service.ConsultaMedicaService;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.ConsultaMedica;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.SituacaoConsultaMedica;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Tag(name = "Consulta Médica", description = "Endpoints destinado ao cadastro de consultas médicas")
@RestController
@RequestMapping("/consulta-medica")
public class ConsultaMedicaController {

    private final ConsultaMedicaService consultaMedicaService;

    public ConsultaMedicaController(ConsultaMedicaService consultaMedicaService) {
        this.consultaMedicaService = consultaMedicaService;
    }

    @PostMapping
    public ResponseEntity<?> criarConsultaMedica(@RequestBody ConsultaMedica consultaMedica) {
        try {
            ConsultaMedica novaConsulta = consultaMedicaService.criarConsultaMedica(consultaMedica);
            return new ResponseEntity<>(novaConsulta, HttpStatus.CREATED);
        } catch (ConsultaMedicaIllegalArgumentException | HorarioDisponivelNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarConsultaMedica(@PathVariable UUID id, @RequestBody ConsultaMedicaDTO consultaMedica) {

        try {
            ConsultaMedica consultaAtualizada = consultaMedicaService.atualizarConsultaMedica(id, consultaMedica.getHorarioDisponivelId());
            return new ResponseEntity<>(consultaAtualizada, HttpStatus.OK);
        } catch (ConsultaMedicaIllegalArgumentException | HorarioDisponivelNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/{id}/aceitar")
    public ResponseEntity<?> aceitarConsulta(@PathVariable UUID id) {
        try {
            ConsultaMedica consultaAceita = consultaMedicaService.aceitarConsulta(id);
            return new ResponseEntity<>(consultaAceita, HttpStatus.OK);
        } catch (ConsultaMedicaIllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/{id}/recusar")
    public ResponseEntity<?> recusarConsulta(@PathVariable UUID id) {
        try {
            ConsultaMedica consultaRecusada = consultaMedicaService.recusarConsulta(id);
            return new ResponseEntity<>(consultaRecusada, HttpStatus.OK);
        } catch (ConsultaMedicaIllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarConsulta(@PathVariable UUID id) {
        try {
            ConsultaMedica consultaCancelada = consultaMedicaService.cancelarConsulta(id);
            return new ResponseEntity<>(consultaCancelada, HttpStatus.OK);
        } catch (ConsultaMedicaIllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaMedica> buscarPorId(@PathVariable UUID id) {
        try {
            ConsultaMedica consultaMedica = consultaMedicaService.buscarPorId(id);
            return new ResponseEntity<>(consultaMedica, HttpStatus.OK);
        } catch (ConsultaMedicaNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ConsultaMedica>> listarTodas() {
        List<ConsultaMedica> consultas = consultaMedicaService.listarTodas();
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaMedica>> listarPorPaciente(@PathVariable UUID pacienteId) {
        List<ConsultaMedica> consultas = consultaMedicaService.listarPorPaciente(pacienteId);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<ConsultaMedica>> listarPorMedico(@PathVariable UUID medicoId) {
        List<ConsultaMedica> consultas = consultaMedicaService.listarPorMedico(medicoId);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/data")
    public ResponseEntity<List<ConsultaMedica>> listarPorData(@RequestParam Date data) {
        List<ConsultaMedica> consultas = consultaMedicaService.listarPorData(data);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<ConsultaMedica>> listarPorPeriodo(@RequestParam Date dataInicio, @RequestParam Date dataFim) {
        List<ConsultaMedica> consultas = consultaMedicaService.listarPorPeriodo(dataInicio, dataFim);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/medico/{medicoId}/status/{status}/periodo")
    public ResponseEntity<List<ConsultaMedica>> listarPorMedicoStatusPeriodo(
        @PathVariable UUID medicoId,
        @PathVariable SituacaoConsultaMedica status,
        @RequestParam Date dataInicio,
        @RequestParam Date dataFim) {
        List<ConsultaMedica> consultas = consultaMedicaService.listarPorMedicoStatusPeriodo(medicoId, status, dataInicio, dataFim);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }
}