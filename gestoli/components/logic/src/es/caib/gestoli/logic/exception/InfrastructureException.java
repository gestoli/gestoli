package es.caib.gestoli.logic.exception;

/**
 * This exception is used to mark (fatal) failures in infrastructure and system code.
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public class InfrastructureException extends RuntimeException {
	private static final long serialVersionUID = -5738806903063156043L;

	public InfrastructureException() {
	}

	public InfrastructureException(String message) {
		super(message);
	}

	public InfrastructureException(String message, Throwable cause) {
		super(message, cause);
	}

	public InfrastructureException(Throwable cause) {
		super(cause);
	}
}
