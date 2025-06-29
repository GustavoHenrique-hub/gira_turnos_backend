package lbty.giraturnos.back.GiraTurnosAPI.infra.dto;


import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.EscalaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class EscalaDTO {

    private Long id;

    private String descricao;

    public EscalaDTO(EscalaEntity escalaEntity) {
        BeanUtils.copyProperties(escalaEntity, this);
    }
}
