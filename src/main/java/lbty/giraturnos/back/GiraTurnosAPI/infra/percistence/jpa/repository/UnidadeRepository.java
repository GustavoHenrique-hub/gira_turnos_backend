package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository;

import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnidadeRepository extends JpaRepository<UnidadeEntity, Long> {

    @Query(value = "SELECT u.* FROM unidade u ORDER BY u.nome", nativeQuery = true)
    List<UnidadeEntity> findAllOrderedByName();
}
