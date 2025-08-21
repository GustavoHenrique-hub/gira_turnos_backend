package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity;

import jakarta.persistence.*;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.EscalaDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.MotivoCancelamentoDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "motivo_cancelamento")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class MotivoCancelamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(){
        if (this.descricao != null){
            this.descricao = this.descricao.toUpperCase();
        }
    }

    public MotivoCancelamentoEntity(MotivoCancelamentoDTO motivoCancelamentoEntityDTO) {
        BeanUtils.copyProperties(motivoCancelamentoEntityDTO, this);
    }
}
