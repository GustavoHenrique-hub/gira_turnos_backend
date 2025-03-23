package lbty.giraturnos.back.GiraTurnosAPI.entity;

//PACKAGES
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.ObjetivoDaVisitaDTO;

//LIBS
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.*;

@Entity
@Table(name = "objetivo_visita")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ObjetivoDaVisitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String objetivo;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(){
        if (this.objetivo != null){
            this.objetivo = this.objetivo.toUpperCase();
        }
    }
    public ObjetivoDaVisitaEntity(ObjetivoDaVisitaDTO objetivoDaVisitaDTO){
        BeanUtils.copyProperties(objetivoDaVisitaDTO, this);
    }

}
