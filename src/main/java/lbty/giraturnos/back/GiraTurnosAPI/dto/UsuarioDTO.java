package lbty.giraturnos.back.GiraTurnosAPI.dto;

import lbty.giraturnos.back.GiraTurnosAPI.entity.*;

import lombok.*;
import org.springframework.beans.*;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public UsuarioDTO (UsuarioEntity usuarioEntity){
        BeanUtils.copyProperties(usuarioEntity, this);
    }

}
