package bside.palmtree.external.oauth.exception;

public class OAuthClientException extends RuntimeException {

	public OAuthClientException(String message) {
		super(message);
	}

	public OAuthClientException(String message, Throwable cause) {
		super(message, cause);
	}
}
