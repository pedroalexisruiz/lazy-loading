package tech.com.co.lazyloading.comando.infraestructura.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.com.co.lazyloading.comando.dominio.entidad.DisplacementPerDay;
import tech.com.co.lazyloading.comando.infraestructura.persistencia.entidad.DisplacementEntity;

@Component
public class DisplacementBuilder {

	@Autowired
	ResultBuilder resultBuilder;

	public DisplacementEntity convertirToEntity(DisplacementPerDay displacementPerDay) {
		if (displacementPerDay == null) {
			return null;
		}
		return new DisplacementEntity(displacementPerDay.getId(), displacementPerDay.getDescription(),
				this.resultBuilder.convertToEntity(displacementPerDay.getResult()));
	}

	public DisplacementPerDay convertToDomain(DisplacementEntity displacementEntity) {
		if (displacementEntity == null) {
			return null;
		}
		return new DisplacementPerDay(displacementEntity.getId(),
				resultBuilder.convertToDomain(displacementEntity.getResult()), displacementEntity.getDescription());
	}

	public List<DisplacementPerDay> convertListToDomain(List<DisplacementEntity> displacementEntities) {
		List<DisplacementPerDay> displacementResults = new ArrayList<DisplacementPerDay>();

		for (DisplacementEntity displacementEntitie : displacementEntities) {
			displacementResults.add(this.convertToDomain(displacementEntitie));
		}

		return displacementResults;
		// return Arrays.stream(displacementEntities.toArray())
		// .map(displacementEntitie ->
		// this.convertirADominio(displacementEntitie)).collect(Collectors.toList());

	}

	public List<DisplacementEntity> convertListToEntities(List<DisplacementPerDay> displacementEntities) {
		List<DisplacementEntity> displacementResults = new ArrayList<DisplacementEntity>();

		for (DisplacementPerDay displacementEntitie : displacementEntities) {
			displacementResults.add(this.convertirToEntity(displacementEntitie));
		}

		return displacementResults;
		// return Arrays.stream(displacementEntities.toArray())
		// .map(displacementEntitie ->
		// this.convertirADominio(displacementEntitie)).collect(Collectors.toList());

	}
}
