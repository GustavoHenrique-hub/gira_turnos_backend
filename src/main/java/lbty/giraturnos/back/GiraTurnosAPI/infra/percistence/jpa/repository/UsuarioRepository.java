package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository;

import lbty.giraturnos.back.GiraTurnosAPI.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query(value = "SELECT * FROM usuario u WHERE u.email = ? AND u.senha = ?", nativeQuery = true)
    UsuarioEntity userLogin(String email, String  senha);
}
