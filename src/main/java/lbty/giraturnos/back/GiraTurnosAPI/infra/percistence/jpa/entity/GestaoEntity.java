package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity;

//PACKAGES
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.GestaoDTO;

//LIBS
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.*;

@Entity
@Table(name = "gestao")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class GestaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column (nullable = false)
    private String nome;

    @NotBlank
    @Column (nullable = false)
    private String cargo;

    private String email;

    private String telefone;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(){
        if (this.nome != null){
            this.nome = this.nome.toUpperCase();
        }
        if (this.cargo != null){
            this.cargo = this.cargo.toUpperCase();
        }
    }

    public GestaoEntity(GestaoDTO gestaoDTO){
        BeanUtils.copyProperties(gestaoDTO, this);
    }

}
