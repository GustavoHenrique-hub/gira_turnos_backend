package lbty.giraturnos.back.GiraTurnosAPI.infra.dto;

import jakarta.validation.constraints.NotBlank;

import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.VisitaEntity;
import lombok.*;
import org.springframework.beans.*;

@Getter
@Setter
@NoArgsConstructor
public class VisitaDTO {

    private Long id;

    @NotBlank(message = "O campo 'Tecnico' não pode ser vazio ou nulo!")
    private TecnicoDTO tecnico;

    @NotBlank(message = "O campo 'Unidade' não pode ser vazio ou nulo!")
    private UnidadeDTO unidade;

    @NotBlank(message = "O campo 'Localização' não pode ser vazio ou nulo!")
    private LocalizacaoDTO localizacao;
    
    @NotBlank(message = "O campo 'Turno' não pode ser vazio ou nulo!")
    private TurnoDTO turno;

    @NotBlank(message = "O campo 'Escala' não pode ser vazio ou nulo!")
    private EscalaDTO escala;

    private UsuarioDTO resposavelRegistro;

    @NotBlank(message = "O campo 'Data/Hora Inicio da Visita' não pode ser vazio ou nulo!")
    private String dataHoraInicioVisita;

    @NotBlank(message = "O campo 'Data/Hora Fim da Visita' não pode ser vazio ou nulo!")
    private String dataHoraFimVisita;

    @NotBlank(message = "O campo 'Objetivo da Visita' não pode ser vazio ou nulo!")
    private String objetivoDaVisita;

    private String dataHoraRegistro;

    public VisitaDTO (VisitaEntity visitaEntity){
        BeanUtils.copyProperties(visitaEntity, this);
        if(visitaEntity != null && visitaEntity.getTecnico() != null){
            this.tecnico = new TecnicoDTO(visitaEntity.getTecnico());
        }
        if(visitaEntity != null && visitaEntity.getUnidade() != null){
            this.unidade = new UnidadeDTO(visitaEntity.getUnidade());
        }
        if(visitaEntity != null && visitaEntity.getTurno() != null){
            this.turno = new TurnoDTO(visitaEntity.getTurno());
        }
        if(visitaEntity != null && visitaEntity.getLocalizacao() != null){
            this.localizacao = new LocalizacaoDTO(visitaEntity.getLocalizacao());
        }
        if(visitaEntity != null && visitaEntity.getResposavelRegistro() != null){
            this.resposavelRegistro = new UsuarioDTO(visitaEntity.getResposavelRegistro());
        }
        if(visitaEntity != null && visitaEntity.getEscala() != null){
            this.escala = new EscalaDTO(visitaEntity.getEscala());
        }
    }
}
