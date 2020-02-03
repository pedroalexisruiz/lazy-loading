package tech.com.co.lazyloading.comando.aplicacion.manejadores;

import org.springframework.transaction.annotation.Transactional;

public interface CommandResponseHandler<C, R> {

	@Transactional
	R execute(C command);
}