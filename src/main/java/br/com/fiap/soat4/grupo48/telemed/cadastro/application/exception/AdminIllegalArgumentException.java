package br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception;

import br.com.fiap.soat4.grupo48.telemed.commons.exception.ApplicationException;

public class AdminIllegalArgumentException extends ApplicationException {
    public AdminIllegalArgumentException(String message) {
        super(message);
    }
}
