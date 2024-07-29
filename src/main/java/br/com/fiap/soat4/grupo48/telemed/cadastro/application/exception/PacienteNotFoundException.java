package br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception;

/**
 * Exception lançada quando um paciente não é encontrado.
 */
public class PacienteNotFoundException extends Exception {

    public PacienteNotFoundException(String message) {
        super(message);
    }
}