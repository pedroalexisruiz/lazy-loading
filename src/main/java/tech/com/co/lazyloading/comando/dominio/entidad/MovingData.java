package tech.com.co.lazyloading.comando.dominio.entidad;

import java.util.Arrays;
import java.util.List;

import tech.com.co.lazyloading.comando.dominio.entidad.util.ValidadorRequeridos;

public class MovingData {
	private String cedula;
	private int workdays;
	private List<Integer>[] weightsPerItem;

	private static final String INVALID_WORKDAYS = "La cantidad de dias no es valida";
	private static final String INVALID_ITEMS_PER_DAY = "La cantidad de items por dia no es valida";
	private static final String INVALID_WEIGHT_PER_ITEM = "El peso de los elementos no es valido";

	public MovingData() {

	}

	public MovingData(String cedula, int workdays, List<Integer>[] weightsPerItem) {
		ValidadorRequeridos.validateWorkDays(workdays, MovingData.INVALID_WORKDAYS);
		ValidadorRequeridos.validateItemsPerDays(weightsPerItem, MovingData.INVALID_ITEMS_PER_DAY);
		ValidadorRequeridos.validateWeightPerItem(weightsPerItem, MovingData.INVALID_WEIGHT_PER_ITEM);

		this.workdays = workdays;
		this.weightsPerItem = weightsPerItem;
	}

	public String getCedula() {
		return cedula;
	}

	public int getWorkdays() {
		return workdays;
	}

	public List<Integer>[] getWeightsPerItem() {
		return weightsPerItem;
	}

	@Override
	public String toString() {
		return "MovingData [cedula=" + cedula + ", workdays=" + workdays + ", weightsPerItem="
				+ Arrays.toString(weightsPerItem) + "]";
	}

}
