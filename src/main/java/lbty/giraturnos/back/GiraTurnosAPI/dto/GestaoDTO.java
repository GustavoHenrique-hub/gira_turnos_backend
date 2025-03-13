package lbty.giraturnos.back.GiraTurnosAPI.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "O campo 'Nome' não pode ser vazio ou nulo!")
    private String nome;

    @NotBlank(message = "O campo 'Cargo' não pode ser vazio ou nulo!")
    private String cargo;

    private String email;

    private String telefone;

    public GestaoDTO(GestaoEntity gestaoEntity){
        BeanUtils.copyProperties(gestaoEntity, this);
    }
}
