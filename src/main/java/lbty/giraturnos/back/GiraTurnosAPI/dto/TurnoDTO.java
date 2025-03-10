package lbty.giraturnos.back.GiraTurnosAPI.dto;

import lbty.giraturnos.back.GiraTurnosAPI.entity.TurnoEntity;
import lombok.*;
import org.springframework.beans.*;

@Getter
@Setter
@NoArgsConstructor
public class TurnoDTO {

    private Long id;
    private Long turno;

    public TurnoDTO (TurnoEntity turnoEntity){
        BeanUtils.copyProperties(turnoEntity, this);
    }
}
