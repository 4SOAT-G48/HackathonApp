package br.com.fiap.soat4.grupo48.telemed.consulta.application.exception;

import br.com.fiap.soat4.grupo48.telemed.commons.exception.ApplicationException;

public class ConsultaMedicaIllegalArgumentException extends ApplicationException {
    public ConsultaMedicaIllegalArgumentException(String message) {
        super(message);
    }
}
