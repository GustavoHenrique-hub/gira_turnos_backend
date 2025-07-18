package lbty.giraturnos.back.GiraTurnosAPI.infra.dto;

import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.TurnoEntity;
import lombok.*;
import org.springframework.beans.*;

@Getter
@Setter
@NoArgsConstructor
public class TurnoDTO {

    private Long id;

    private String turno;

    public TurnoDTO (TurnoEntity turnoEntity){
        BeanUtils.copyProperties(turnoEntity, this);
    }
}
