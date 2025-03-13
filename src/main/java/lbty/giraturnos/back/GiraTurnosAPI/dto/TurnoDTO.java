package lbty.giraturnos.back.GiraTurnosAPI.dto;

import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.entity.TurnoEntity;
import lombok.*;
import org.springframework.beans.*;

@Getter
@Setter
@NoArgsConstructor
public class TurnoDTO {

    private Long id;

    @NotBlank(message = "O campo 'Turno' não pode ser vazio ou nulo!")
    private Long turno;

    public TurnoDTO (TurnoEntity turnoEntity){
        BeanUtils.copyProperties(turnoEntity, this);
    }
}
