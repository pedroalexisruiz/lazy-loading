package tech.com.co.lazyloading.comando.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tech.com.co.lazyloading.comando.dominio.entidad.Inspector;
import tech.com.co.lazyloading.comando.dominio.entidad.util.GeneradorDeFecha;
import tech.com.co.lazyloading.comando.dominio.repositorio.DisplacementRepository;
import tech.com.co.lazyloading.comando.infraestructura.persistencia.repositorios.ResultRepositoryH2;

@Configuration
public class ServicioBean {

	@Bean
	public Inspector createInspector(DisplacementRepository displacementRepository,
			GeneradorDeFecha generadorDeFechas) {
		return new Inspector(displacementRepository, generadorDeFechas);
	}

	@Bean
	public GeneradorDeFecha createDateGenerator() {
		return GeneradorDeFecha.obtenerInstancia();
	}

	@Bean
	public DisplacementRepository createDisplacementsRepository() {
		return new ResultRepositoryH2();
	}
}
