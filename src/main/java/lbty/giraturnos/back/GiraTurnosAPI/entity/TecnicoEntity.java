package lbty.giraturnos.back.GiraTurnosAPI.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
}
