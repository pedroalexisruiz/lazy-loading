package tech.com.co.lazyloading.comando.infraestructura.exception;

public class InvalidFileContentException extends RuntimeException{

	public InvalidFileContentException(String message) {
		super(message);
	}
}
