package lbty.giraturnos.back.GiraTurnosAPI.repository;

import lbty.giraturnos.back.GiraTurnosAPI.entity.ObjetivoDaVisitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObjetivoDaVisitaRepository extends JpaRepository<ObjetivoDaVisitaEntity, Long> {
    @Query(value = "SELECT ov.* FROM objetivo_visita ov ORDER BY ov.objetivo", nativeQuery = true)
    List<ObjetivoDaVisitaEntity> findAllOrderedByName();
}
