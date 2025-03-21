package lbty.giraturnos.back.GiraTurnosAPI.dto;


import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.entity.TecnicoEntity;
import lombok.*;
import org.springframework.beans.*;


@Getter
@Setter
@NoArgsConstructor
public class TecnicoDTO {


    private Long id;

    @NotBlank(message = "O campo 'Nome' não pode ser vazio ou nulo!")
    private String nome;

    @NotBlank(message = "O campo 'Email' não pode ser vazio ou nulo!")
    private String email;


    public TecnicoDTO(TecnicoEntity tecnicoEntity){
        BeanUtils.copyProperties(tecnicoEntity, this);
    }
}
