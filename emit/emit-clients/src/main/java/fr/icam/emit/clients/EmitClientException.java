package fr.icam.emit.clients;

public class EmitClientException extends Exception {

	private static final long serialVersionUID = 20171221165030L;
	
	private int code;
	
	private String message;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public EmitClientException(int code, String message) {
		super(code + ": " + message);
		this.code = code;
		this.message = message;
	}
	
	public EmitClientException(int code, String message, Throwable throwable) {
		super(code + ": " + message, throwable);
		this.code = code;
		this.message = message;
	}

}
