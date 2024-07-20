package br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception;

import br.com.fiap.soat4.grupo48.telemed.commons.exception.ApplicationException;

/**
 * Exception lançada quando um médico não é encontrado.
 */
public class MedicoNotFoundException extends ApplicationException {

    public MedicoNotFoundException(String message) {
        super(message);
    }
}