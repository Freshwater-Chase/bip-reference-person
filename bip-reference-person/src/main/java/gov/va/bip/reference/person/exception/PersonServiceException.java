package gov.va.bip.reference.person.exception;

import org.springframework.http.HttpStatus;

import gov.va.bip.framework.exception.BipRuntimeException;
import gov.va.bip.framework.messages.MessageKey;
import gov.va.bip.framework.messages.MessageSeverity;

/**
 * Runtime exception for the Person service.
 *
 * @author aburkholder
 */
public class PersonServiceException extends BipRuntimeException {
	private static final long serialVersionUID = -5224277215368080694L;

	/**
	 * Constructs a new RuntimeException for the Person service with the specified detail key, message, severity, and status.
	 * The cause is not initialized.
	 *
	 * @see BipRuntimeException#BipRuntimeException(String, String, MessageSeverity, HttpStatus)
	 *
	 * @param key - the consumer-facing key that can uniquely identify the nature of the exception
	 * @param severity - the severity of the event: FATAL (500 series), ERROR (400 series), WARN (200 series), or INFO/DEBUG/TRACE
	 * @param status - the HTTP Status code that applies best to the encountered problem, see
	 *            <a href="https://tools.ietf.org/html/rfc7231">https://tools.ietf.org/html/rfc7231</a>
	 * @param params - arguments to fill in any params in the MessageKey message (e.g. value for {0})
	 */
	public PersonServiceException(final MessageKey key, final MessageSeverity severity, final HttpStatus status, final String... params) {
		super(key, severity, status, params);
	}

	/**
	 * Constructs a new RuntimeException with the specified detail key, message, severity, status, and cause.
	 *
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 *
	 * @param key - the consumer-facing key that can uniquely identify the nature of the exception
	 * @param severity - the severity of the event: FATAL (500 series), ERROR (400 series), WARN (200 series), or INFO/DEBUG/TRACE
	 * @param status - the HTTP Status code that applies best to the encountered problem, see
	 *            <a href="https://tools.ietf.org/html/rfc7231">https://tools.ietf.org/html/rfc7231</a>
	 * @param cause - the throwable that caused this throwable
	 * @param params - arguments to fill in any params in the MessageKey message (e.g. value for {0})
	 */
	public PersonServiceException(final MessageKey key, final MessageSeverity severity, final HttpStatus status, final Throwable cause, final String... params) {
		super(key, severity, status, cause, params);
	}
}
