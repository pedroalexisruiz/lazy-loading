package tech.com.co.lazyloading.comando.infraestructura.controladores;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tech.com.co.lazyloading.comando.aplicacion.entidad.CommandMovingData;
import tech.com.co.lazyloading.comando.aplicacion.manejadores.DisplacementCalculatorHandler;
import tech.com.co.lazyloading.comando.aplicacion.manejadores.FileLoadHandler;

@RestController
@RequestMapping("/displacements")
@CrossOrigin(origins = "http://localhost:3000")
public class DisplacementsCommandController {

	private final DisplacementCalculatorHandler displacementCalculatorHandler;
	private final FileLoadHandler fileLoadHandler;

	@Autowired
	public DisplacementsCommandController(DisplacementCalculatorHandler displacementCalculatorHandler,
			FileLoadHandler fileLoadHandler) {
		this.displacementCalculatorHandler = displacementCalculatorHandler;
		this.fileLoadHandler = fileLoadHandler;
	}

	@PostMapping(value = "/{cedula}")
	public ResponseEntity<Resource> registrarEntrada(@PathVariable("cedula") String cedula,
			@RequestParam(value = "file", required = true) MultipartFile file) {

		File fileUploaded = displacementCalculatorHandler.execute(new CommandMovingData(cedula, file)).getDatos();
		System.out.println("ruta, " + fileUploaded.getAbsolutePath());

		Resource resource = this.fileLoadHandler.execute(fileUploaded.getName()).getDatos();
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}