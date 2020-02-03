package tech.com.co.lazyloading.comando.dominio.entidad;

import java.time.LocalDateTime;
import java.util.List;

public class DisplacementResult {

	private Long id;
	private String cedula;
	private LocalDateTime generationDate;
	private int workdays;
	private List<DisplacementPerDay> displacementsPerDay;

	public DisplacementResult() {
	}

	public DisplacementResult(Long id, String cedula, LocalDateTime generationDate, int workdays,
			List<DisplacementPerDay> displacementsPerDay) {
		this.id = id;
		this.cedula = cedula;
		this.displacementsPerDay = displacementsPerDay;
		this.generationDate = generationDate;
		this.workdays = workdays;
	}

	public List<DisplacementPerDay> getDisplacementsPerDay() {
		return displacementsPerDay;
	}

	public void setDisplacementsPerDay(List<DisplacementPerDay> displacementsPerDay) {
		this.displacementsPerDay = displacementsPerDay;
	}

	public int getWorkdays() {
		return this.workdays;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public LocalDateTime getGenerationDate() {
		return this.generationDate;
	}

	public Long getId() {
		return this.id;
	}
}
