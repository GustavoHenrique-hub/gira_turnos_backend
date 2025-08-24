package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository;

import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.VisitaEntity;

import lbty.giraturnos.back.GiraTurnosAPI.infra.projection.VisitaSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface VisitaRepository extends JpaRepository<VisitaEntity, Long> {

    @Query(value = "SELECT * FROM visita VISITA " +
            "WHERE (:tecnico IS NULL OR VISITA.id_tecnico = :tecnico)" +
            "AND (:unidade IS NULL OR VISITA.id_unidade = :unidade)" +
            "AND (:objetivodavisita IS NULL OR VISITA.id_objetivo_visita = :objetivodavisita)" +
            "AND (:turno IS NULL OR VISITA.id_turno = :turno)" +
            "AND (:responsavelRegistro IS NULL OR VISITA.id_usuario = :responsavelRegistro)" +
            "AND (:datadavisita IS NULL OR VISITA.data_da_visita = :datadavisita)" +
            "AND (:horarioiniciovisita IS NULL OR VISITA.horario_inicio_visita = :horarioiniciovisita)" +
            "AND (:horariofimvisita IS NULL OR VISITA.horario_fim_visita = :horariofimvisita)", nativeQuery = true)
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

    @Query("""
    SELECT
      VISITA.id                          AS id,
      VISITA.unidade.nome                AS unidadeNome,
      VISITA.tecnico.nome                AS tecnicoNome,
      VISITA.dataHoraInicioVisita        AS dataHoraInicioVisita,
      VISITA.dataHoraFimVisita           AS dataHoraFimVisita,
      VISITA.objetivoDaVisita            AS objetivoDaVisita,
      VISITA.localizacao.id              AS localizacaoId,
      VISITA.localizacao.local            AS localizacaoLocal
    FROM VisitaEntity VISITA
    WHERE VISITA.dataHoraInicioVisita BETWEEN :inicio AND :fim
""")
    List<VisitaSummary> visitaPaginator(@Param("inicio") String inicio, @Param("fim") String fim);
}
