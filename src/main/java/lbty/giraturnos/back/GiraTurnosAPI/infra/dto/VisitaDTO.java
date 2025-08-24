package lbty.giraturnos.back.GiraTurnosAPI.infra.dto;

import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.VisitaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class VisitaDTO {

    private Long id;
    private TecnicoDTO tecnico;
    private UnidadeDTO unidade;
    private LocalizacaoDTO localizacao;
    private TurnoDTO turno;
    private EscalaDTO escala;
    private UsuarioDTO responsavelRegistro;
    private String dataHoraInicioVisita;
    private String dataHoraFimVisita;
    private String objetivoDaVisita;
    private String dataHoraRegistro;
    private String numCard;

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
        if(visitaEntity != null && visitaEntity.getResponsavelRegistro() != null){
            this.responsavelRegistro = new UsuarioDTO(visitaEntity.getResponsavelRegistro());
        }
        if(visitaEntity != null && visitaEntity.getEscala() != null){
            this.escala = new EscalaDTO(visitaEntity.getEscala());
        }
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }
}
