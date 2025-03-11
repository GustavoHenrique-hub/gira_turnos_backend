package lbty.giraturnos.back.GiraTurnosAPI.dto;

import lbty.giraturnos.back.GiraTurnosAPI.entity.GestaoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class GestaoDTO {
    private Long id;
    private String nome;
    private String cargo;
    private String email;
    private String telefone;

    public GestaoDTO(GestaoEntity gestaoEntity){
        BeanUtils.copyProperties(gestaoEntity, this);
    }
}
