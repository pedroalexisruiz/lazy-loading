package tech.com.co.lazyloading.comando.dominio.servicio;

import tech.com.co.lazyloading.comando.dominio.entidad.DisplacementResult;
import tech.com.co.lazyloading.comando.dominio.entidad.MovingData;

public interface DisplacementsCalculatorService {

	public DisplacementResult registerMovingData(MovingData movingData);
}
