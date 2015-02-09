package error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class InternalErrorException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public InternalErrorException(Throwable cause, String message) {
		super(cause, Response.status(500).
		    entity(message).type("text/plain").build());
	}
}
