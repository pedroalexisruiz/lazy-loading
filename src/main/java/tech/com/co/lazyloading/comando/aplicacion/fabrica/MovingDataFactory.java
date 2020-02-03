package tech.com.co.lazyloading.comando.aplicacion.fabrica;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.com.co.lazyloading.comando.aplicacion.entidad.CommandMovingData;
import tech.com.co.lazyloading.comando.dominio.entidad.MovingData;
import tech.com.co.lazyloading.comando.dominio.servicio.MovingDataFileManager;

@Component
public class MovingDataFactory {

	private MovingDataFileManager movingDataExtractor;

	@Autowired
	public MovingDataFactory(MovingDataFileManager movingDataExtractor) {
		this.movingDataExtractor = movingDataExtractor;
	}

	public MovingData create(CommandMovingData commandMovingData) {
		int workDays, itemsPerDay = 0, indexDay = 0;
		List<Integer>[] weightsPerItems;

		List<Integer> data = this.movingDataExtractor.extractLines(commandMovingData.getFile());
		workDays = data.get(0);
		ArrayList[] arrayLists = new ArrayList[workDays];
		weightsPerItems = arrayLists;

		for (int i = 0; i < workDays; i++) {
			indexDay = indexDay + itemsPerDay + 1;
			itemsPerDay = data.get(indexDay);

			weightsPerItems[i] = new ArrayList<Integer>();
			for (int j = 0; j < itemsPerDay; j++) {
				weightsPerItems[i].add(data.get(indexDay + j + 1));
			}
		}

		return new MovingData(commandMovingData.getCedula(), workDays, weightsPerItems);
	}
}
