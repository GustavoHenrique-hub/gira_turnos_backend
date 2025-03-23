package lbty.giraturnos.back.GiraTurnosAPI.entity;

//PACKAGES
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.UsuarioDTO;

//LIBS
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String senha;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(){
        if (this.nome != null){
            this.nome = this.nome.toUpperCase();
        }
    }

    public UsuarioEntity(UsuarioDTO usuarioDTO){
        BeanUtils.copyProperties(usuarioDTO, this);
    }
}
