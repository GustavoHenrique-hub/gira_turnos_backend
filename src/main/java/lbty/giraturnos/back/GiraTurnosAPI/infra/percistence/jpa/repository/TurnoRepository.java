package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository;

import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.TurnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TurnoRepository extends JpaRepository<TurnoEntity, Long> {

    @Query(value = "SELECT tr.* FROM turno tr ORDER BY l.local", nativeQuery = true)
    List<TurnoEntity> findAllOrderedByName();
}
