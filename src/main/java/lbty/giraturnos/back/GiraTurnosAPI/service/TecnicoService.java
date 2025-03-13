package lbty.giraturnos.back.GiraTurnosAPI.service;

import lbty.giraturnos.back.GiraTurnosAPI.dto.TecnicoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.entity.TecnicoEntity;
import lbty.giraturnos.back.GiraTurnosAPI.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TecnicoService {


    @Autowired
    private TecnicoRepository tecnicoRepository;

    public List<TecnicoDTO> listAll(){
        List<TecnicoEntity> tecnico = tecnicoRepository.findAllOrderedByName();
        return tecnico.stream().map(TecnicoDTO::new).toList();
    }

    public ResponseEntity<Map<String, String>> insertTecnico(TecnicoDTO tecnicoDTO){
        TecnicoEntity tecnicoEntity = new TecnicoEntity(tecnicoDTO);
        Map<String, String> response = new HashMap<>();
        try{
            tecnicoRepository.save(tecnicoEntity);
            response.put("WARN", "Tecnico cadastrado com sucesso!!");
            return ResponseEntity.ok(response);
        }
        catch(RuntimeException re){
            response.put("ERROR", "Erro ao cadastrar aluno: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateTecnico(TecnicoDTO tecnicoDTO, Long id){
        Map<String, String> response = new HashMap<>();
        Optional<TecnicoEntity> tecnicoEntityOptional = tecnicoRepository.findById(id);

        try{
            if(tecnicoEntityOptional.isPresent()){
                TecnicoEntity tecnicoEntity = tecnicoEntityOptional.get();
                tecnicoEntity.setEmail(tecnicoDTO.getEmail());
                tecnicoEntity.setNome(tecnicoDTO.getNome());
                tecnicoRepository.save(tecnicoEntity);

                response.put("WARN", "Técnico alterado com sucesso!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Técnico não encontrado!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao alterar Técnico: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> deleteTecnico(Long id){
        TecnicoEntity tecnicoEntity = tecnicoRepository.findById(id).get();
        Map<String, String> response = new HashMap<>();

        try{
            if(tecnicoEntity != null){
                tecnicoRepository.delete(tecnicoEntity);
                response.put("WARN", "Técnico removido com sucesso!!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Técnico não encontrado!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao deletar técnico: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
