package br.com.fiap.soat4.grupo48.telemed.commons.exception;

import java.io.Serial;

/**
 * Exception da aplicação, usado para identificar as Exceptions disparadas pela aplicação.
 */
public class ApplicationException extends Exception {

    // serialVersionUID é usado para garantir a compatibilidade de versões na serialização de objetos.
    @Serial
    private static final long serialVersionUID = 5307268515715579642L;

    /**
     * Construtor para ApplicationException com apenas a mensagem de erro.
     *
     * @param message A mensagem de erro que a exceção deve conter.
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * Construtor para ApplicationException com a mensagem de erro e a causa.
     *
     * @param message A mensagem de erro que a exceção deve conter.
     * @param cause   A causa raiz da exceção (outra exceção que foi a causa desta).
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
