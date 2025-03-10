package lbty.giraturnos.back.GiraTurnosAPI.dto;

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
    private String objetivo;

    public ObjetivoDaVisitaDTO(ObjetivoDaVisitaEntity objetivoDaVisitaEntity){
        BeanUtils.copyProperties(objetivoDaVisitaEntity, this);
    }
}
