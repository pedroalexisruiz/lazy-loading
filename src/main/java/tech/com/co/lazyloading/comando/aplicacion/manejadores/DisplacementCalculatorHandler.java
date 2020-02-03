package tech.com.co.lazyloading.comando.aplicacion.manejadores;

import java.io.File;
import org.springframework.stereotype.Component;

import tech.com.co.lazyloading.comando.aplicacion.entidad.CommandMovingData;
import tech.com.co.lazyloading.comando.aplicacion.fabrica.MovingDataFactory;
import tech.com.co.lazyloading.comando.dominio.entidad.DisplacementResult;
import tech.com.co.lazyloading.comando.dominio.entidad.MovingData;
import tech.com.co.lazyloading.comando.dominio.servicio.DisplacementsCalculatorService;
import tech.com.co.lazyloading.comando.dominio.servicio.MovingDataFileManager;

@Component
public class DisplacementCalculatorHandler implements CommandResponseHandler<CommandMovingData, CommandResponse<File>> {

	private final DisplacementsCalculatorService displacementsCalculatorService;
	private final MovingDataFileManager movingDataFileManager;
	private final MovingDataFactory movingDataFactory;

	public DisplacementCalculatorHandler(DisplacementsCalculatorService displacementsCalculatorService,
			MovingDataFileManager movingDataFileManager, MovingDataFactory movingDataFactory) {
		this.movingDataFactory = movingDataFactory;
		this.displacementsCalculatorService = displacementsCalculatorService;
		this.movingDataFileManager = movingDataFileManager;
	}

	@Override
	public CommandResponse<File> execute(CommandMovingData commandDisplacementData) {
		System.out.println("En handler, " + commandDisplacementData);
		MovingData movingData = this.movingDataFactory.create(commandDisplacementData);
		DisplacementResult displacementResult = this.displacementsCalculatorService.registerMovingData(movingData);
		return new CommandResponse<File>(this.movingDataFileManager.writeMovingDataReport(displacementResult));
	}

}
