package tech.com.co.lazyloading.comando.dominio.repositorio;

import java.util.List;

import tech.com.co.lazyloading.comando.dominio.entidad.DisplacementResult;

public interface DisplacementRepository {

	/**
	 * Records the result
	 * 
	 * @param displacementResult
	 */
	void recordResults(DisplacementResult displacementResult);

	DisplacementResult findById(Long id);

	List<DisplacementResult> findByCedula(String cedula);

	void deleteAll();
}
