package tech.com.co.lazyloading.comando.dominio.entidad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import tech.com.co.lazyloading.comando.dominio.entidad.util.GeneradorDeFecha;
import tech.com.co.lazyloading.comando.dominio.repositorio.DisplacementRepository;
import tech.com.co.lazyloading.comando.dominio.servicio.DisplacementsCalculatorService;

public class Inspector implements DisplacementsCalculatorService {

	private static final int MIN_WEIGHT = 50;
	private static final int NUMERO_MAXIMO_MOTOS = 10;

	private static final char PRIMERA_LETRA_PLACA_NO_VALIDA = 'A';
	public static final String DIA_NO_HABIL = "No se permite el ingreso del vehiculo en día no habil";

	public static final String EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehículo ya se encuentra en el parqueadero.";
	public static final String EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehículo no se encuentra en el parqueadero.";
	public static final String NO_HAY_ESPACIOS_DISPONIBLES = "No hay espacios disponibles en el parqueadero.";

	private final DisplacementRepository displacementRepository;
	private final GeneradorDeFecha dateGenerator;

	public Inspector(DisplacementRepository displacementRepository, GeneradorDeFecha dateGenerator) {
		super();
		this.displacementRepository = displacementRepository;
		this.dateGenerator = dateGenerator;
	}

	@Override
	public DisplacementResult registerMovingData(MovingData movingData) {
		List<Integer> weightsPerDay, weightsPerDayCopy;
		int displacementsPerDayQuantity, previousWeight, weightPerItem;
		List<DisplacementPerDay> displacementsPerDay = new ArrayList<>();
		DisplacementResult result = new DisplacementResult(null, movingData.getCedula(),
				this.dateGenerator.obtenerHoraLocalActual(), movingData.getWorkdays(), null);

		for (int i = 0; i < movingData.getWeightsPerItem().length; i++) {
			weightsPerDay = movingData.getWeightsPerItem()[i];
			Collections.sort(weightsPerDay);

			weightsPerDayCopy = new ArrayList<Integer>(weightsPerDay);

			displacementsPerDayQuantity = 0;
			previousWeight = 0;

			for (int j = 0; j < weightsPerDay.size(); j++) {
				weightPerItem = weightsPerDay.get(j) + previousWeight;

				if (weightPerItem >= Inspector.MIN_WEIGHT) {
					displacementsPerDayQuantity++;
					previousWeight = 0;
					weightsPerDayCopy.remove(weightPerItem);
				} else {
					previousWeight = findBestWeightForGetTheMinimum(weightPerItem, i, weightsPerDayCopy);
				}
			}
			displacementsPerDay.add(new DisplacementPerDay(null, result,
					formatDisplacementDescription(i + 1, displacementsPerDayQuantity)));
		}
		
		result.setDisplacementsPerDay(displacementsPerDay);
		return result;
	}

	private String formatDisplacementDescription(int numDay, int quantity) {
		return String.format("Case #%s: %s", numDay, quantity);
	}

	private int findBestWeightForGetTheMinimum(int currentWeight, int currentIndex, List<Integer> weights) {
		int bestWeight = 101;
		int aux;

		for (int i = currentIndex + 1; i < weights.size(); i++) {
			aux = currentWeight + weights.get(i);
			if (aux >= Inspector.MIN_WEIGHT) {
				if (Math.abs(aux - Inspector.MIN_WEIGHT) < Math.abs(bestWeight - Inspector.MIN_WEIGHT)) {
					bestWeight = aux;
				}
			}
		}

		if (bestWeight == 101) {
			bestWeight = weights.get(weights.size() - 1);
		}

		weights.remove(bestWeight);

		return bestWeight;
	}

}
