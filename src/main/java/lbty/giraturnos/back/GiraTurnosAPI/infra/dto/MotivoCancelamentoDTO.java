package lbty.giraturnos.back.GiraTurnosAPI.infra.dto;

import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.MotivoCancelamentoEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.TurnoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class MotivoCancelamentoDTO {

    private Long id;

    private String descricao;

    public MotivoCancelamentoDTO (MotivoCancelamentoEntity motivoCancelamentoEntity){
        BeanUtils.copyProperties(motivoCancelamentoEntity, this);
    }
}
