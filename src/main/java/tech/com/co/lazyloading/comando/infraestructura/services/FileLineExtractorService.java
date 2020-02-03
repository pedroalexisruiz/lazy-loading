package tech.com.co.lazyloading.comando.infraestructura.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import tech.com.co.lazyloading.comando.dominio.entidad.DisplacementPerDay;
import tech.com.co.lazyloading.comando.dominio.entidad.DisplacementResult;
import tech.com.co.lazyloading.comando.dominio.servicio.MovingDataFileManager;
import tech.com.co.lazyloading.comando.infraestructura.exception.CreatingFileException;
import tech.com.co.lazyloading.comando.infraestructura.exception.InvalidFileException;

@Component
public class FileLineExtractorService implements MovingDataFileManager {

	private final static String INVALID_FILE = "Archivo no válido";
	private final static String INVALID_CONTENT = "Los datos en el archivo no son válidos";
	private final static String ERROR_CREATING_FILE = "Ocurrió un error al crear el archivo";

	public List<Integer> extractLines(MultipartFile file) {
		BufferedReader br;
		List<Integer> lines = new ArrayList<Integer>();

		try {
			String line;
			InputStream is = file.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				lines.add(Integer.parseInt(line));
			}
		} catch (IOException e) {
			throw new InvalidFileException(FileLineExtractorService.INVALID_FILE);
		} catch (NumberFormatException e) {
			throw new InvalidFileException(FileLineExtractorService.INVALID_CONTENT);
		}

		return lines;
	}

	public File writeMovingDataReport(DisplacementResult result) {

		List<String> lines = this.getReportText(result.getDisplacementsPerDay());
		Path file = Paths.get(String.format("%s-%s.txt", result.getCedula(), result.getGenerationDate()));
		try {
			Files.write(file, lines, StandardCharsets.UTF_8);
			return file.toFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CreatingFileException(FileLineExtractorService.ERROR_CREATING_FILE);
		}
	}

	private List<String> getReportText(List<DisplacementPerDay> displacementsPerDay) {
		List<String> lines = new ArrayList<>();
		for (int i = 0; i < displacementsPerDay.size(); i++) {
			lines.add(displacementsPerDay.get(i).getDescription());
		}

		return lines;
	}
}
