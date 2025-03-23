package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity;

//PACKAGES
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.TurnoDTO;

//LIBS
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.*;
@Entity
@Table(name = "turno")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TurnoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String turno;

    @PrePersist
    @PreUpdate
    public void PrepersistAndUpdate(){
        if(this.turno != null){
            this.turno = this.turno.toUpperCase();
        }
    }

    public TurnoEntity (TurnoDTO turnoDTO){
        BeanUtils.copyProperties(turnoDTO, this);
    }
}
