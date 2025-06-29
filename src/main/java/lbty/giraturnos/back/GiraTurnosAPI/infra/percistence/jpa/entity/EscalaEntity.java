package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.EscalaDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "escala")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class EscalaEntity {

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

    public EscalaEntity(EscalaDTO escalaDTO) {
        BeanUtils.copyProperties(escalaDTO, this);
    }
}
