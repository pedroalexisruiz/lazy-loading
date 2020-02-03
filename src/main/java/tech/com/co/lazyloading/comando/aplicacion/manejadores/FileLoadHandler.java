package tech.com.co.lazyloading.comando.aplicacion.manejadores;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import tech.com.co.lazyloading.comando.dominio.servicio.MovingDataFileManager;

@Component
public class FileLoadHandler implements CommandResponseHandler<String, CommandResponse<Resource>> {

	private final MovingDataFileManager movingDataFileManager;

	public FileLoadHandler(MovingDataFileManager movingDataFileManager) {
		this.movingDataFileManager = movingDataFileManager;
	}

	@Override
	public CommandResponse<Resource> execute(String fileName) {
		return new CommandResponse<Resource>(this.movingDataFileManager.loadFileAsResource(fileName));
	}

}
