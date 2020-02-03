package tech.com.co.lazyloading.comando.infraestructura.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.com.co.lazyloading.comando.dominio.entidad.DisplacementPerDay;
import tech.com.co.lazyloading.comando.dominio.entidad.DisplacementResult;
import tech.com.co.lazyloading.comando.infraestructura.persistencia.entidad.DisplacementEntity;
import tech.com.co.lazyloading.comando.infraestructura.persistencia.entidad.ResultEntity;

@Component
public class ResultBuilder {

	@Autowired
	DisplacementBuilder displacementBuilder;

	public ResultEntity convertToEntity(DisplacementResult displacementResult) {
		if (displacementResult == null) {
			return null;
		}
		return new ResultEntity(displacementResult.getId(), displacementResult.getGenerationDate(),
				displacementResult.getCedula(), displacementResult.getWorkdays(),
				this.displacementBuilder.convertListToEntities(displacementResult.getDisplacementsPerDay()));
	}

	public DisplacementResult convertToDomain(ResultEntity resultEntity) {
		if (resultEntity == null) {
			return null;
		}
		return new DisplacementResult(resultEntity.getId(), resultEntity.getCedula(), resultEntity.getFecha(),
				resultEntity.getWorkdays(),
				this.displacementBuilder.convertListToDomain(resultEntity.getDisplacements()));
	}

	public List<DisplacementResult> convertListToDomain(List<ResultEntity> displacementEntities) {
		List<DisplacementResult> displacementResults = new ArrayList<DisplacementResult>();

		for (ResultEntity displacementEntitie : displacementEntities) {
			displacementResults.add(this.convertToDomain(displacementEntitie));
		}

		return displacementResults;

	}
}
