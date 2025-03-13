package lbty.giraturnos.back.GiraTurnosAPI.repository;

import lbty.giraturnos.back.GiraTurnosAPI.entity.GestaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GestaoRepository extends JpaRepository<GestaoEntity, Long> {

    @Query(value = "SELECT g.* FROM gestao g ORDER BY g.nome", nativeQuery = true)
    List<GestaoEntity> findAllOrderedByName();
}
