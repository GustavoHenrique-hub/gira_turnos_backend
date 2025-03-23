package lbty.giraturnos.back.GiraTurnosAPI.entity;

//PACKAGES
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.LocalizacaoDTO;

//LIBS
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "localizacao")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class LocalizacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column (nullable = false)
    private String local;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(){
        if (this.local != null){
            this.local = this.local.toUpperCase();
        }
    }

    public LocalizacaoEntity(LocalizacaoDTO localizacaoDTO){
        BeanUtils.copyProperties(localizacaoDTO, this);
    }
}
