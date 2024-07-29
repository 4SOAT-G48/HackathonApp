package br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception;

import br.com.fiap.soat4.grupo48.telemed.commons.exception.ApplicationException;

public class PacienteIllegalArgumentException extends ApplicationException {
    public PacienteIllegalArgumentException(String message) {
        super(message);
    }
}
