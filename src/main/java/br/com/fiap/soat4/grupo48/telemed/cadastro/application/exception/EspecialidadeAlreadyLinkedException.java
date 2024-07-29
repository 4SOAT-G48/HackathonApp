package br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception;

import br.com.fiap.soat4.grupo48.telemed.commons.exception.ApplicationException;

/**
 * Exception lançada quando uma especialidade não é encontrada.
 */
public class EspecialidadeAlreadyLinkedException extends ApplicationException {

    public EspecialidadeAlreadyLinkedException(String message) {
        super(message);
    }
}