package lbty.giraturnos.back.GiraTurnosAPI.dto;

import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.entity.*;

import lombok.*;
import org.springframework.beans.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class VisitaDTO {

    private Long id;

    @NotBlank(message = "O campo 'Tecnico' não pode ser vazio ou nulo!")
    private TecnicoDTO tecnico;

    @NotBlank(message = "O campo 'Unidade' não pode ser vazio ou nulo!")
    private UnidadeDTO unidade;

    @NotBlank(message = "O campo 'Objetivo da Visita' não pode ser vazio ou nulo!")
    private ObjetivoDaVisitaDTO objetivoDaVisita;

    @NotBlank(message = "O campo 'Turno' não pode ser vazio ou nulo!")
    private TurnoDTO turno;

    private UsuarioDTO resposavelRegistro;

    @NotBlank(message = "O campo 'Data da Visita' não pode ser vazio ou nulo!")
    private Date dataDaVisita;

    @NotBlank(message = "O campo 'Horário início da Visita' não pode ser vazio ou nulo!")
    private String horarioInicioVisita;

    @NotBlank(message = "O campo 'Horário fim da Visita' não pode ser vazio ou nulo!")
    private String horarioFimVisita;

    private String dataHoraRegistro;

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
