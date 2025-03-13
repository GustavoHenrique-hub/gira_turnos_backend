package lbty.giraturnos.back.GiraTurnosAPI.dto;

import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.entity.UnidadeEntity;
import lombok.*;
import org.springframework.beans.*;

@Getter
@Setter
@NoArgsConstructor
public class UnidadeDTO {

    private Long id;

    @NotBlank(message = "O campo 'Nome' não pode ser vazio ou nulo!")
    private String nome;

    @NotBlank(message = "O campo 'Email da Unidade' não pode ser vazio ou nulo!")
    private String emailUnidade;

    public UnidadeDTO(UnidadeEntity unidadeEntity){
        BeanUtils.copyProperties(unidadeEntity, this);
    }
}
