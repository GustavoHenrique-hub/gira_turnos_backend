package lbty.giraturnos.back.GiraTurnosAPI.service;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.UsuarioDTO;
import lbty.giraturnos.back.GiraTurnosAPI.entity.UsuarioEntity;
import lbty.giraturnos.back.GiraTurnosAPI.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> listAll(){
        List<UsuarioEntity> usuario = usuarioRepository.findAll();
        return usuario.stream().map(UsuarioDTO::new).toList();
    }

    public ResponseEntity<Map<String, String>> insertUsuario(UsuarioDTO usuarioDTO){
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioDTO);
        Map<String, String> response = new HashMap<>();

        try{
            usuarioRepository.save(usuarioEntity);
            response.put("WARN", "Usuário cadastrado com sucesso!!");
            return ResponseEntity.ok(response);
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao cadastrar usuário: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateUsuario(UsuarioDTO usuarioDTO, Long id){
        Map<String, String> response = new HashMap<>();
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findById(id);

        try{
            if(usuarioEntityOptional.isPresent()){
                UsuarioEntity usuarioEntity = usuarioEntityOptional.get();
                usuarioEntity.setNome(usuarioDTO.getNome());
                usuarioEntity.setEmail(usuarioDTO.getEmail());
                usuarioEntity.setSenha(usuarioDTO.getSenha());
                usuarioRepository.save(usuarioEntity);

                response.put("WARN", "Usuário alterado com sucesso!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Usuário não encontrado!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao alterar usuário: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> deleteUsuario(Long id){
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).get();
        Map<String, String> response = new HashMap<>();

        try{
            if(usuarioEntity != null){
                usuarioRepository.delete(usuarioEntity);
                response.put("WARN", "Usuário removido com sucesso!!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Usuário não encontrado!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao deletar usuário: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

