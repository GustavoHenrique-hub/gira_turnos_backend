package lbty.giraturnos.back.GiraTurnosAPI.application.usecases;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.UnidadeDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.UnidadeEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<UnidadeDTO> listAll(){
        List<UnidadeEntity> unidade = unidadeRepository.findAllOrderedByName();
        return unidade.stream().map(UnidadeDTO::new).toList();
    }

    public ResponseEntity<Map<String, String>> insertUnidade(UnidadeDTO unidadeDTO){
        UnidadeEntity unidadeEntity = new UnidadeEntity(unidadeDTO);
        Map<String, String> response = new HashMap<>();

        try{
            unidadeRepository.save(unidadeEntity);
            response.put("WARN", "Unidade cadastrada com sucesso!!");
            return ResponseEntity.ok(response);
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao cadastrar unidade: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateUnidade(UnidadeDTO unidadeDTO, Long id){
        Map<String, String> response = new HashMap<>();
        Optional<UnidadeEntity> unidadeEntityOptional = unidadeRepository.findById(id);

        try{
            if(unidadeEntityOptional.isPresent()){
                UnidadeEntity unidadeEntity = unidadeEntityOptional.get();
                unidadeEntity.setNome(unidadeDTO.getNome());
                unidadeRepository.save(unidadeEntity);

                response.put("WARN", "Unidade alterada com sucesso!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Unidade não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao alterar unidade: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> deleteUnidade(Long id){
        UnidadeEntity unidadeEntity = unidadeRepository.findById(id).get();
        Map<String, String> response = new HashMap<>();

        try{
            if(unidadeEntity != null){
                unidadeRepository.delete(unidadeEntity);
                response.put("WARN", "Unidade removida com sucesso!!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Unidade não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao deletar unidade: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

