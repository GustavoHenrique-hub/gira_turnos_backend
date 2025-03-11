package lbty.giraturnos.back.GiraTurnosAPI.repository;

import lbty.giraturnos.back.GiraTurnosAPI.entity.TecnicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TecnicoRepository extends JpaRepository<TecnicoEntity, Long> {

    @Query(value = "SELECT t.* FROM tecnico t ORDER BY t.nome", nativeQuery = true)
    List<TecnicoEntity> findAllOrderedByName();
}
