package tech.com.co.lazyloading.comando.infraestructura.persistencia.repositorios.implejpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.com.co.lazyloading.comando.infraestructura.persistencia.entidad.ResultEntity;

@Repository
public interface ResultJpaRepository extends JpaRepository<ResultEntity, Long> {

	Optional<ResultEntity> findById(Long id);

	Optional<List<ResultEntity>> findByCedula(String cedula);
}
