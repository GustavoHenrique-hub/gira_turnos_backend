package lbty.giraturnos.back.GiraTurnosAPI.entity;

//PACKAGES
import lbty.giraturnos.back.GiraTurnosAPI.dto.VisitaDTO;

//LIBS
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.*;

import java.util.Date;

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
    @JoinColumn(name = "id_objetivo_visita", nullable = false)
    private ObjetivoDaVisitaEntity objetivoDaVisita;

    @ManyToOne
    @JoinColumn(name = "id_turno", nullable = false)
    private TurnoEntity turno;

    @NotBlank
    @Column(nullable = false)
    private Date dataDaVisita;

    @NotBlank
    @Column(nullable = false)
    private String horarioInicioVisita;

    @NotBlank
    @Column(nullable = false)
    private String horarioFimVisita;

    @NotBlank
    @Column(nullable = false)
    private String dataHoraRegistro;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity resposavelRegistro;

    public VisitaEntity (VisitaDTO visitaDTO){
        BeanUtils.copyProperties(visitaDTO, this);
        if(visitaDTO != null && visitaDTO.getTecnico() != null){
            this.tecnico = new TecnicoEntity(visitaDTO.getTecnico())
        }
        if(visitaDTO != null && visitaDTO.getUnidade() != null){
            this.unidade = new TecnicoEntity(visitaDTO.getUnidade())
        }
        if(visitaDTO != null && visitaDTO.getObjetivoDaVisita() != null){
            this.objetivoDaVisita = new TecnicoEntity(visitaDTO.getObjetivoDaVisita())
        }
        if(visitaDTO != null && visitaDTO.getTurno() != null){
            this.turno = new TecnicoEntity(visitaDTO.getTurno())
        }
        if(visitaDTO != null && visitaDTO.getResposavelRegistro() != null){
            this.resposavelRegistro = new TecnicoEntity(visitaDTO.getResposavelRegistro())
        }
    }

}
