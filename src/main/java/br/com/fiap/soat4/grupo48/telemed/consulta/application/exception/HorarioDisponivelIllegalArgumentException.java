package br.com.fiap.soat4.grupo48.telemed.consulta.application.exception;

import br.com.fiap.soat4.grupo48.telemed.commons.exception.ApplicationException;

public class HorarioDisponivelIllegalArgumentException extends ApplicationException {
    public HorarioDisponivelIllegalArgumentException(String message) {
        super(message);
    }
}
