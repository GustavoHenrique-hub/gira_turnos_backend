package lbty.giraturnos.back.GiraTurnosAPI.dto;

import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.entity.ObjetivoDaVisitaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class ObjetivoDaVisitaDTO {

    private Long id;

    @NotBlank(message = "O campo 'Objetivo' não pode ser vazio ou nulo!")
    private String objetivo;

    public ObjetivoDaVisitaDTO(ObjetivoDaVisitaEntity objetivoDaVisitaEntity){
        BeanUtils.copyProperties(objetivoDaVisitaEntity, this);
    }
}
