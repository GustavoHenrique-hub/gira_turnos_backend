package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity;

//PACKAGES

import jakarta.persistence.*;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.VisitaDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "visita")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class VisitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tecnico", nullable = false)
    private TecnicoEntity tecnico;

    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false)
    private UnidadeEntity unidade;

    @ManyToOne
    @JoinColumn(name = "id_turno", nullable = false)
    private TurnoEntity turno;

    @ManyToOne
    @JoinColumn(name = "id_localizacao", nullable = false)
    private LocalizacaoEntity localizacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity responsavelRegistro;

    @ManyToOne
    @JoinColumn(name = "id_escala", nullable = false)
    private EscalaEntity escala;

    @Column(nullable = false)
    private String dataHoraInicioVisita;

    @Column(nullable = false)
    private String dataHoraFimVisita;

    @Column(nullable = false)
    private String dataHoraRegistro;

    @Column(name = "objetivo_visita")
    private String objetivoDaVisita;

    @Column
    private String numCard;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate() {
        if (this.objetivoDaVisita != null) {
            this.objetivoDaVisita = this.objetivoDaVisita.toUpperCase();
        }
    }

    public VisitaEntity(VisitaDTO visitaDTO) {
        BeanUtils.copyProperties(visitaDTO, this);
        if (visitaDTO != null && visitaDTO.getTecnico() != null) {
            this.tecnico = new TecnicoEntity(visitaDTO.getTecnico());
        }
        if (visitaDTO != null && visitaDTO.getUnidade() != null) {
            this.unidade = new UnidadeEntity(visitaDTO.getUnidade());
        }
        if (visitaDTO != null && visitaDTO.getTurno() != null) {
            this.turno = new TurnoEntity(visitaDTO.getTurno());
        }
        if (visitaDTO != null && visitaDTO.getLocalizacao() != null) {
            this.localizacao = new LocalizacaoEntity(visitaDTO.getLocalizacao());
        }
        if (visitaDTO != null && visitaDTO.getResponsavelRegistro() != null) {
            this.responsavelRegistro = new UsuarioEntity(visitaDTO.getResponsavelRegistro());
        }
        if (visitaDTO != null && visitaDTO.getEscala() != null) {
            this.escala = new EscalaEntity(visitaDTO.getEscala());
        }
    }

}
