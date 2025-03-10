package lbty.giraturnos.back.GiraTurnosAPI.dto;


import lbty.giraturnos.back.GiraTurnosAPI.entity.TecnicoEntity;
import lombok.*;
import org.springframework.beans.*;


@Getter
@Setter
@NoArgsConstructor
public class TecnicoDTO {

    private Long id;
    private String nome;
    private String email;


    public TecnicoDTO(TecnicoEntity tecnicoEntity){
        BeanUtils.copyProperties(tecnicoEntity, this);
    }
}
