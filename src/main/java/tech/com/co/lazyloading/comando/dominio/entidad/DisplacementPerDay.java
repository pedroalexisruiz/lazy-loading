package tech.com.co.lazyloading.comando.dominio.entidad;

public class DisplacementPerDay {

	private Long id;
	private DisplacementResult result;
	private String description;

	public DisplacementPerDay() {
	}

	public DisplacementPerDay(Long id, DisplacementResult result, String description) {
		this.id = id;
		this.result = result;
		this.description = description;
	}

	public DisplacementPerDay(String description) {
		this.description = description;
	}

	public DisplacementResult getResult() {
		return result;
	}

	public void setResult(DisplacementResult result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}
}
