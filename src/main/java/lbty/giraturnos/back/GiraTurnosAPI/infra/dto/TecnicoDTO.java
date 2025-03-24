package lbty.giraturnos.back.GiraTurnosAPI.infra.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.TecnicoEntity;
import lombok.*;
import org.springframework.beans.*;


@Getter
@Setter
@NoArgsConstructor
public class TecnicoDTO {
    
    @NotBlank(message = "O campo 'Nome' não pode ser vazio ou nulo!")
    @Schema(description = "Nome do técnico", example = "Gustavo Silva")
    private String nome;

    @NotBlank(message = "O campo 'Email' não pode ser vazio ou nulo!")
    @Schema(description = "Email institucional do técnico", example = "gustavo.silva@libertyti.com.br")
    private String email;


    public TecnicoDTO(TecnicoEntity tecnicoEntity){
        BeanUtils.copyProperties(tecnicoEntity, this);
    }
}
