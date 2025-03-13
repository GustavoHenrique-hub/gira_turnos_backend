package lbty.giraturnos.back.GiraTurnosAPI.entity;

import jakarta.persistence.*;
import lbty.giraturnos.back.GiraTurnosAPI.dto.GestaoPorUnidadeDTO;
import lbty.giraturnos.back.GiraTurnosAPI.dto.VisitaDTO;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "gestao_por_unidade")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class GestaoPorUnidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_gestao", nullable = false)
    private GestaoEntity gestao;

    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false)
    private UnidadeEntity unidade;

    public GestaoPorUnidadeEntity (GestaoPorUnidadeDTO gestaoPorUnidadeDTO){
        BeanUtils.copyProperties(gestaoPorUnidadeDTO, this);
        if(gestaoPorUnidadeDTO != null && gestaoPorUnidadeDTO.getGestao() != null){
            this.gestao = new GestaoEntity(gestaoPorUnidadeDTO.getGestao());
        }
        if(gestaoPorUnidadeDTO != null && gestaoPorUnidadeDTO.getUnidade() != null){
            this.unidade = new UnidadeEntity(gestaoPorUnidadeDTO.getUnidade());
        }
    }
}
