package lbty.giraturnos.back.GiraTurnosAPI.dto;

import lbty.giraturnos.back.GiraTurnosAPI.entity.*;

import lombok.*;
import org.springframework.beans.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class VisitaDTO {

    private Long id;
    private TecnicoDTO tecnico;
    private UnidadeDTO unidade;
    private ObjetivoDaVisitaDTO objetivoDaVisita;
    private TurnoEntity turno;
    private Date dataDaVisita;
    private String horarioInicioVisita;
    private String horarioFimVisita;
    private String dataHoraRegistro;
    private UsuarioDTO resposavelRegistro;

    public VisitaDTO (VisitaEntity visitaEntity){
        BeanUtils.copyProperties(visitaEntity, this);
        if(visitaEntity != null && visitaEntity.getTecnico() != null){
            this.tecnico = new TecnicoDTO(visitaEntity.getTecnico());
        }
        if(visitaEntity != null && visitaEntity.getUnidade() != null){
            this.unidade = new UnidadeDTO(visitaEntity.getUnidade());
        }
        if(visitaEntity != null && visitaEntity.getObjetivoDaVisita() != null){
            this.objetivoDaVisita = new ObjetivoDaVisitaDTO(visitaEntity.getObjetivoDaVisita());
        }
        if(visitaEntity != null && visitaEntity.getTurno() != null){
            this.turno = new TurnoDTO(visitaEntity.getTurno());
        }
        if(visitaEntity != null && visitaEntity.getResposavelRegistro() != null){
            this.resposavelRegistro = new UsuarioDTO(visitaEntity.getResposavelRegistro());
        }
    }
}
