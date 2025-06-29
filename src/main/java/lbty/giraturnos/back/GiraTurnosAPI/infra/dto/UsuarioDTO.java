package lbty.giraturnos.back.GiraTurnosAPI.infra.dto;

import jakarta.validation.constraints.NotBlank;

import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.UsuarioEntity;
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
