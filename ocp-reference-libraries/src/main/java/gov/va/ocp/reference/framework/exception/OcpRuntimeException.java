package gov.va.ocp.reference.framework.exception;

/**
 * Custom extension of RuntimeException so that we can raise this for exceptions we have no intention
 * of handling and need to raise but for some reason cannot raise
 * java's RuntimeException or allow the original exception to simply propagate.
 *
 * @author jshrader
 */
public class OcpRuntimeException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2598842813684506358L;

	/** Server name exception occurred on */
	public static final String SERVER_NAME = System.getProperty("server.name");

	/**
	 * Instantiates a new exception.
	 */
	public OcpRuntimeException() {
		super();
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public OcpRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param message the message
	 */
	public OcpRuntimeException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param cause the cause
	 */
	public OcpRuntimeException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Gets the server name.
	 *
	 * @return the server name
	 */
	public final String getServerName() {
		return SERVER_NAME;
	}
}
