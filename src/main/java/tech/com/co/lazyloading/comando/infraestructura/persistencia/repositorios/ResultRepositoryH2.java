package tech.com.co.lazyloading.comando.infraestructura.persistencia.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import tech.com.co.lazyloading.comando.dominio.entidad.DisplacementResult;
import tech.com.co.lazyloading.comando.dominio.repositorio.DisplacementRepository;
import tech.com.co.lazyloading.comando.infraestructura.persistencia.builder.ResultBuilder;
import tech.com.co.lazyloading.comando.infraestructura.persistencia.entidad.ResultEntity;
import tech.com.co.lazyloading.comando.infraestructura.persistencia.repositorios.implejpa.ResultJpaRepository;

public class ResultRepositoryH2 implements DisplacementRepository {

	@Autowired
	ResultJpaRepository resultRepository;
	@Autowired
	ResultBuilder resultBuilder;

	@Override
	public void recordResults(DisplacementResult displacementResult) {
		resultRepository.save(resultBuilder.convertToEntity(displacementResult));
	}

	@Override
	public DisplacementResult findById(Long id) {
		Optional<ResultEntity> ticket = resultRepository.findById(id);
		return resultBuilder.convertToDomain(ticket.orElse(null));
	}

	@Override
	public List<DisplacementResult> findByCedula(String cedula) {
		Optional<List<ResultEntity>> ticket = resultRepository.findByCedula(cedula);
		return resultBuilder.convertListToDomain(ticket.orElse(null));
	}

	@Override
	public void deleteAll() {
		resultRepository.deleteAll();
	}
}
