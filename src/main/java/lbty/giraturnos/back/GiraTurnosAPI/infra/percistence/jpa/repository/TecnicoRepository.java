package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository;

import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.TecnicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TecnicoRepository extends JpaRepository<TecnicoEntity, Long> {

    @Query(value =
            "SELECT DISTINCT t.id, t.email, t.nome " +
            "FROM tecnico t " +
            "WHERE t.email <> 'EXPIRADO' " +
            "ORDER BY t.nome", nativeQuery = true)
    List<TecnicoEntity> findAllOrderedByName();
}
