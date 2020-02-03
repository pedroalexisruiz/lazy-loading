package tech.com.co.lazyloading.comando.infraestructura.persistencia.entidad;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Result")
public class ResultEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;
	@Column(name = "cedula", nullable = false, length = 11)
	private String cedula;
	@Column(name = "workdays", nullable = false)
	private Integer workdays;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "result")
	private List<DisplacementEntity> displacements;
}
