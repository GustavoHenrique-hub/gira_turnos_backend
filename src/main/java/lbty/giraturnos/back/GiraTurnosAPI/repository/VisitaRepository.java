package lbty.giraturnos.back.GiraTurnosAPI.repository;

import lbty.giraturnos.back.GiraTurnosAPI.entity.VisitaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface VisitaRepository extends JpaRepository<VisitaEntity, Long> {
    @Query(value = "SELECT * FROM visita vt " +
            "WHERE (:tecnico IS NULL OR vt.id_tecnico = :tecnico)" +
            "AND (:unidade IS NULL OR vt.id_unidade = :unidade)" +
            "AND (:objetivodavisita IS NULL OR vt.id_objetivo_visita = :objetivodavisita)" +
            "AND (:turno IS NULL OR vt.id_turno = :turno)" +
            "AND (:responsavelRegistro IS NULL OR vt.id_usuario = :responsavelRegistro)" +
            "AND (:datadavisita IS NULL OR vt.data_da_visita = :datadavisita)" +
            "AND (:horarioiniciovisita IS NULL OR vt.horario_inicio_visita = :horarioiniciovisita)" +
            "AND (:horariofimvisita IS NULL OR vt.horario_fim_visita = :horariofimvisita)", nativeQuery = true)
    List<VisitaEntity> findVisitaWithFilters(
            @Param("tecnico") Long tecnico,
            @Param("unidade") Long unidade,
            @Param("objetivodavisita") Long objetivodavisita,
            @Param("turno") Long turno,
            @Param("responsavelRegistro") Long responsavelRegistro,
            @Param("datadavisita") String datadavisita,
            @Param("horarioiniciovisita") String horarioiniciovisita,
            @Param("horariofimvisita") String horariofimvisita
    );
}
