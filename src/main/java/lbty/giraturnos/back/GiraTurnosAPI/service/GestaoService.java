package lbty.giraturnos.back.GiraTurnosAPI.service;

import lbty.giraturnos.back.GiraTurnosAPI.dto.GestaoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.entity.GestaoEntity;
import lbty.giraturnos.back.GiraTurnosAPI.repository.GestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GestaoService {

    @Autowired
    private GestaoRepository gestaoRepository;

    public List<GestaoDTO> listAll(){
        List<GestaoEntity> gestao = gestaoRepository.findAllOrderedByName();
        return gestao.stream().map(GestaoDTO::new).toList();
    }

    public ResponseEntity<Map<String, String>> insertGestao(GestaoDTO gestaoDTO){
        GestaoEntity gestaoEntity = new GestaoEntity(gestaoDTO);
        Map<String, String> response = new HashMap<>();

        try{
            gestaoRepository.save(gestaoEntity);
            response.put("WARN", "Gestao cadastrada com sucesso!!");
            return ResponseEntity.ok(response);
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao cadastrar aluno: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateGestao(GestaoDTO gestaoDTO, Long id){
        Map<String, String> response = new HashMap<>();
        Optional<GestaoEntity> gestaoEntityOptional = gestaoRepository.findById(id);

        try{
            if(gestaoEntityOptional.isPresent()){
                GestaoEntity gestaoEntity = gestaoEntityOptional.get();
                gestaoEntity.setNome(gestaoDTO.getNome());
                gestaoEntity.setCargo(gestaoDTO.getCargo());
                gestaoEntity.setEmail(gestaoDTO.getEmail());
                gestaoEntity.setTelefone(gestaoDTO.getTelefone());
                gestaoRepository.save(gestaoEntity);

                response.put("WARN", "Gestao alterada com sucesso!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Gestao não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao alterar gestao: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> deleteGestao(Long id){
        GestaoEntity gestaoEntity = gestaoRepository.findById(id).get();
        Map<String, String> response = new HashMap<>();

        try{
            if(gestaoEntity != null){
                gestaoRepository.delete(gestaoEntity);
                response.put("WARN", "Gestao removida com sucesso!!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Gestao não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao deletar gestao: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
