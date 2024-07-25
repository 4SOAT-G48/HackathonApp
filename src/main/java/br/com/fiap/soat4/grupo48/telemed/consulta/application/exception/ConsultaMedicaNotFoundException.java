package br.com.fiap.soat4.grupo48.telemed.consulta.application.exception;

import br.com.fiap.soat4.grupo48.telemed.commons.exception.ApplicationException;

/**
 * Exception lançada quando um médico não é encontrado.
 */
public class ConsultaMedicaNotFoundException extends ApplicationException {

    public ConsultaMedicaNotFoundException(String message) {
        super(message);
    }
}