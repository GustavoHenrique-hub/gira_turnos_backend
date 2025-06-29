package lbty.giraturnos.back.GiraTurnosAPI.infra.dto;

import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.LocalizacaoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class LocalizacaoDTO {

    private Long id;

    private String local;

    public LocalizacaoDTO(LocalizacaoEntity localizacaoEntity){
        BeanUtils.copyProperties(localizacaoEntity, this);
    }
}
