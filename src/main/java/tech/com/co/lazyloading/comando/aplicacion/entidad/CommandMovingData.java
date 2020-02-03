package tech.com.co.lazyloading.comando.aplicacion.entidad;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandMovingData {
	private String cedula;
	private MultipartFile file;

	@Override
	public String toString() {
		return "CommandMovingData [cedula=" + cedula + ", file=" + file.getName() + "]";
	}

}
