package lbty.giraturnos.back.GiraTurnosAPI.application.usecases;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.GestaoPorUnidadeDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.GestaoEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.GestaoPorUnidadeEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.UnidadeEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository.GestaoPorUnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GestaoPorUnidadeService {

    @Autowired
    private GestaoPorUnidadeRepository gestaoPorUnidadeRepository;

    public List<GestaoPorUnidadeDTO> listAll(){
        List<GestaoPorUnidadeEntity> gestaoPorUnidade = gestaoPorUnidadeRepository.findAll();
        return gestaoPorUnidade.stream().map(GestaoPorUnidadeDTO::new).toList();
    }

    public ResponseEntity<Map<String, String>> insertGestaoPorUnidade(GestaoPorUnidadeDTO gestaoPorUnidadeDTO){
        GestaoPorUnidadeEntity gestaoPorUnidadeEntity = new GestaoPorUnidadeEntity(gestaoPorUnidadeDTO);
        Map<String, String> response = new HashMap<>();

        try{
            gestaoPorUnidadeRepository.save(gestaoPorUnidadeEntity);
            response.put("WARN", "Gestão por unidade cadastrada com sucesso!!");
            return ResponseEntity.ok(response);
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao cadastrar gestão por unidade: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateGestaoPorUnidade(GestaoPorUnidadeDTO gestaoPorUnidadeDTO, Long id){
        Map<String, String> response = new HashMap<>();
        Optional<GestaoPorUnidadeEntity> gestaoPorUnidadeEntityOptional = gestaoPorUnidadeRepository.findById(id);

        try{
            if(gestaoPorUnidadeEntityOptional.isPresent()){
                GestaoPorUnidadeEntity gestaoPorUnidadeEntity = gestaoPorUnidadeEntityOptional.get();
                gestaoPorUnidadeEntity.setGestao(new GestaoEntity(gestaoPorUnidadeDTO.getGestao()));
                gestaoPorUnidadeEntity.setUnidade(new UnidadeEntity(gestaoPorUnidadeDTO.getUnidade()));
                gestaoPorUnidadeRepository.save(gestaoPorUnidadeEntity);

                response.put("WARN", "Gestão por unidade alterada com sucesso!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Gestão por unidade não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao alterar gestão por unidade: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> deleteGestaoPorUnidade(Long id){
        GestaoPorUnidadeEntity gestaoPorUnidadeEntity = gestaoPorUnidadeRepository.findById(id).get();
        Map<String, String> response = new HashMap<>();

        try{
            if(gestaoPorUnidadeEntity != null){
                gestaoPorUnidadeRepository.delete(gestaoPorUnidadeEntity);
                response.put("WARN", "Gestão por unidade removida com sucesso!!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Gestão por unidade não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao deletar gestão por unidade: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

