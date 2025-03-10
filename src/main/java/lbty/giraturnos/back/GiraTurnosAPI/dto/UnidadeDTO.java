package lbty.giraturnos.back.GiraTurnosAPI.dto;

import lbty.giraturnos.back.GiraTurnosAPI.entity.UnidadeEntity;
import lombok.*;
import org.springframework.beans.*;

@Getter
@Setter
@NoArgsConstructor
public class UnidadeDTO {

    private Long id;

    private String nomeUnidade;

    private String emailUnidade;

    public UnidadeDTO(UnidadeEntity unidadeEntity){
        BeanUtils.copyProperties(unidadeEntity, this);
    }
}
