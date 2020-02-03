package tech.com.co.lazyloading.comando.dominio.excepcion;

public class InvalidItemsPerDayException extends RuntimeException{

	public InvalidItemsPerDayException(String message) {
		super(message);
	}
}
