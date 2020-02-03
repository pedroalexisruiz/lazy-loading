package tech.com.co.lazyloading.comando.dominio.entidad.util;

import java.util.Iterator;
import java.util.List;

import tech.com.co.lazyloading.comando.dominio.excepcion.InvalidItemsPerDayException;
import tech.com.co.lazyloading.comando.dominio.excepcion.InvalidWorkdaysException;

public final class ValidadorRequeridos {
	private static final int MIN_VALUE = 1;
	private static final int MAX_WORKDAYS = 500;
	private static final int MAX_ITEMS_PER_DAY = 100;
	private static final int MAX_WEIGHT_PER_ITEM = 100;

	private ValidadorRequeridos() {

	}

	public static void validateWorkDays(int workdays, String errorMessage) {
		if (workdays < ValidadorRequeridos.MIN_VALUE || workdays > ValidadorRequeridos.MAX_WORKDAYS) {
			throw new InvalidWorkdaysException(errorMessage);
		}
	}

	public static void validateItemsPerDays(List<Integer>[] weightPerItemMatriz, String errorMessage) {
		int itemsPerDay;
		for (List<Integer> is : weightPerItemMatriz) {
			itemsPerDay = is.size();
			if (itemsPerDay < ValidadorRequeridos.MIN_VALUE || itemsPerDay > ValidadorRequeridos.MAX_ITEMS_PER_DAY) {
				throw new InvalidItemsPerDayException(errorMessage);
			}
		}
	}

	public static void validateWeightPerItem(List<Integer>[] weightPerItemMatriz, String errorMessage) {

		List<Integer> weightPerItemArray;

		for (int i = 0; i < weightPerItemMatriz.length; i++) {
			weightPerItemArray = weightPerItemMatriz[i];

			for (int weightPerItem : weightPerItemArray) {
				if (weightPerItem < ValidadorRequeridos.MIN_VALUE
						|| weightPerItem > ValidadorRequeridos.MAX_WEIGHT_PER_ITEM) {
					throw new InvalidWorkdaysException(errorMessage);
				}
			}
		}

	}

}
