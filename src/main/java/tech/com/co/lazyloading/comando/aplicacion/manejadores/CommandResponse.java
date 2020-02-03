package tech.com.co.lazyloading.comando.aplicacion.manejadores;

public class CommandResponse<T> {

	private T data;

	public CommandResponse(T data) {
		this.data = data;
	}

	public T getDatos() {
		return data;
	}
}
