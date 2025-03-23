package lbty.giraturnos.back.GiraTurnosAPI.infra.dto;

import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.entity.*;

import lombok.*;
import org.springframework.beans.*;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O campo 'Nome' não pode ser vazio ou nulo!")
    private String nome;

    @NotBlank(message = "O campo 'Email' não pode ser vazio ou nulo!")
    private String email;

    @NotBlank(message = "O campo 'Senha' não pode ser vazio ou nulo!")
    private String senha;

    public UsuarioDTO (UsuarioEntity usuarioEntity){
        BeanUtils.copyProperties(usuarioEntity, this);
    }

}
