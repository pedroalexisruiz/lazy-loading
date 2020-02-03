package tech.com.co.lazyloading.comando.dominio.servicio;

import java.io.File;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import tech.com.co.lazyloading.comando.dominio.entidad.DisplacementResult;

public interface MovingDataFileManager {

	public List<Integer> extractLines(MultipartFile file);

	public File writeMovingDataReport(DisplacementResult result);
}
