package lbty.giraturnos.back.GiraTurnosAPI.application.usecases;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.LocalizacaoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.entity.LocalizacaoEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public List<LocalizacaoDTO> listAll(){
        List<LocalizacaoEntity> localizacao = localizacaoRepository.findAllOrderedByName();
        return localizacao.stream().map(LocalizacaoDTO::new).toList();
    }

    public ResponseEntity<Map<String, String>> insertLocalizacao(LocalizacaoDTO localizacaoDTO){
        LocalizacaoEntity localizacaoEntity = new LocalizacaoEntity(localizacaoDTO);
        Map<String, String> response = new HashMap<>();

        try{
            localizacaoRepository.save(localizacaoEntity);
            response.put("WARN", "Localização cadastrada com sucesso!!");
            return ResponseEntity.ok(response);
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao cadastrar localização: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateLocalizacao(LocalizacaoDTO localizacaoDTO, Long id){
        Map<String, String> response = new HashMap<>();
        Optional<LocalizacaoEntity> localizacaoEntityOptional = localizacaoRepository.findById(id);

        try{
            if(localizacaoEntityOptional.isPresent()){
                LocalizacaoEntity localizacaoEntity = localizacaoEntityOptional.get();
                localizacaoEntity.setLocal(localizacaoDTO.getLocal());
                localizacaoRepository.save(localizacaoEntity);

                response.put("WARN", "Localização alterada com sucesso!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Localização não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao alterar localização: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> deleteLocalizacao(Long id){
        LocalizacaoEntity localizacaoEntity = localizacaoRepository.findById(id).get();
        Map<String, String> response = new HashMap<>();

        try{
            if(localizacaoEntity != null){
                localizacaoRepository.delete(localizacaoEntity);
                response.put("WARN", "Localização removida com sucesso!!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Localização não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao deletar localização: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
