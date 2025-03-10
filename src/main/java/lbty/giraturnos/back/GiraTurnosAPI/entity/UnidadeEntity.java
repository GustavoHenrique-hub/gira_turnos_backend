package lbty.giraturnos.back.GiraTurnosAPI.entity;

//PACKAGES
import lbty.giraturnos.back.GiraTurnosAPI.dto.UnidadeDTO;

//LIBS
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.*;

@Entity
@Table(name = "unidade")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UnidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String emailUnidade;

    @PrePersist
    @PreUpdate

    public void prePersistAndUpdate(){
        if (this.nome != null){
            this.nome = this.nome.toUpperCase();
        }
    }

    public UnidadeEntity(UnidadeDTO unidadeDTO){
        BeanUtils.copyProperties(unidadeDTO, this);
    }
}
