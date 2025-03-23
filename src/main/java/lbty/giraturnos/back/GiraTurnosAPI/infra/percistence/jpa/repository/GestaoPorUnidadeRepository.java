package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository;

import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.GestaoPorUnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestaoPorUnidadeRepository extends JpaRepository<GestaoPorUnidadeEntity, Long> {
}
