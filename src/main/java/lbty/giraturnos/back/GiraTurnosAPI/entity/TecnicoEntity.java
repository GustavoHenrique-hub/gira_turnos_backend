package lbty.giraturnos.back.GiraTurnosAPI.entity;

//PACKAGES
import lbty.giraturnos.back.GiraTurnosAPI.dto.TecnicoDTO;

//LIBS
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "tecnico")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TecnicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(){
        if (this.nome != null){
            this.nome = this.nome.toUpperCase();
        }
    }

    public TecnicoEntity(TecnicoDTO tecnicoDTO){
        BeanUtils.copyProperties(tecnicoDTO, this);
    }
}
