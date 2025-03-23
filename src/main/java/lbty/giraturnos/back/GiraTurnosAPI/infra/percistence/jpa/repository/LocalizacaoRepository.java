package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository;

import lbty.giraturnos.back.GiraTurnosAPI.entity.LocalizacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalizacaoRepository extends JpaRepository<LocalizacaoEntity, Long> {

    @Query(value = "SELECT l.* FROM localizacao l ORDER BY l.local", nativeQuery = true)
    List<LocalizacaoEntity> findAllOrderedByName();
}
