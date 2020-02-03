package tech.com.co.lazyloading.comando.dominio.excepcion;

public class InvalidWeightPerItemException extends RuntimeException{

	public InvalidWeightPerItemException(String message) {
		super(message);
	}
}
