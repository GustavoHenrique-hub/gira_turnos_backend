package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity;

//PACKAGES
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.VisitaDTO;

//LIBS
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.*;

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
    private UsuarioEntity resposavelRegistro;

    @ManyToOne
    @JoinColumn(name = "id_escala", nullable = false)
    private EscalaEntity escala;

    @NotBlank
    @Column(nullable = false)
    private String dataHoraInicioVisita;

    @NotBlank
    @Column(nullable = false)
    private String dataHoraFimVisita;

    @NotBlank
    @Column(nullable = false)
    private String dataHoraRegistro;

    @NotBlank
    @Column(name = "objetivo_visita", nullable = false)
    private String objetivoDaVisita;

    @Column
    private String numCard;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(){
        if (this.objetivoDaVisita != null){
            this.objetivoDaVisita = this.objetivoDaVisita.toUpperCase();
        }
    }

    public VisitaEntity (VisitaDTO visitaDTO){
        BeanUtils.copyProperties(visitaDTO, this);
        if(visitaDTO != null && visitaDTO.getTecnico() != null){
            this.tecnico = new TecnicoEntity(visitaDTO.getTecnico());
        }
        if(visitaDTO != null && visitaDTO.getUnidade() != null){
            this.unidade = new UnidadeEntity(visitaDTO.getUnidade());
        }
        if(visitaDTO != null && visitaDTO.getTurno() != null){
            this.turno = new TurnoEntity(visitaDTO.getTurno());
        }
        if(visitaDTO != null && visitaDTO.getLocalizacao() != null){
            this.localizacao = new LocalizacaoEntity(visitaDTO.getLocalizacao());
        }
        if(visitaDTO != null && visitaDTO.getResposavelRegistro() != null){
            this.resposavelRegistro = new UsuarioEntity(visitaDTO.getResposavelRegistro());
        }
        if(visitaDTO != null && visitaDTO.getEscala() != null){
            this.escala = new EscalaEntity(visitaDTO.getEscala());
        }
    }

}
