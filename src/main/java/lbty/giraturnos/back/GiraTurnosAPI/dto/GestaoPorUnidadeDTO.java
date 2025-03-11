package lbty.giraturnos.back.GiraTurnosAPI.dto;

import lbty.giraturnos.back.GiraTurnosAPI.entity.GestaoPorUnidadeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class GestaoPorUnidadeDTO {

    private Long id;
    private GestaoDTO gestao;
    private UnidadeDTO unidade;

    public GestaoPorUnidadeDTO (GestaoPorUnidadeEntity gestaoPorUnidadeEntity){
        BeanUtils.copyProperties(gestaoPorUnidadeEntity, this);
        if(gestaoPorUnidadeEntity != null && gestaoPorUnidadeEntity.getGestao() != null){
            this.gestao = new GestaoDTO(gestaoPorUnidadeEntity.getGestao());
        }
        if(gestaoPorUnidadeEntity != null && gestaoPorUnidadeEntity.getUnidade() != null){
            this.unidade = new UnidadeDTO(gestaoPorUnidadeEntity.getUnidade());
        }
    }
}
