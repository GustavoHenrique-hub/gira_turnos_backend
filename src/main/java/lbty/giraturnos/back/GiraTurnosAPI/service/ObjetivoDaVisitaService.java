package lbty.giraturnos.back.GiraTurnosAPI.service;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.ObjetivoDaVisitaDTO;
import lbty.giraturnos.back.GiraTurnosAPI.entity.ObjetivoDaVisitaEntity;
import lbty.giraturnos.back.GiraTurnosAPI.repository.ObjetivoDaVisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ObjetivoDaVisitaService {

    @Autowired
    private ObjetivoDaVisitaRepository objetivoDaVisitaRepository;

    public List<ObjetivoDaVisitaDTO> listAll(){
        List<ObjetivoDaVisitaEntity> objetivoDaVisita = objetivoDaVisitaRepository.findAllOrderedByName();
        return objetivoDaVisita.stream().map(ObjetivoDaVisitaDTO::new).toList();
    }

    public ResponseEntity<Map<String, String>> insertObjetivoDaVisita(ObjetivoDaVisitaDTO objetivoDaVisitaDTO){
        ObjetivoDaVisitaEntity objetivoDaVisitaEntity = new ObjetivoDaVisitaEntity(objetivoDaVisitaDTO);
        Map<String, String> response = new HashMap<>();

        try{
            objetivoDaVisitaRepository.save(objetivoDaVisitaEntity);
            response.put("WARN", "Objetivo da visita cadastrado com sucesso!!");
            return ResponseEntity.ok(response);
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao cadastrar objetivo da visita: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateObjetivoDaVisita(ObjetivoDaVisitaDTO objetivoDaVisitaDTO, Long id){
        Map<String, String> response = new HashMap<>();
        Optional<ObjetivoDaVisitaEntity> objetivoDaVisitaEntityOptional = objetivoDaVisitaRepository.findById(id);

        try{
            if(objetivoDaVisitaEntityOptional.isPresent()){
                ObjetivoDaVisitaEntity objetivoDaVisitaEntity = objetivoDaVisitaEntityOptional.get();
                objetivoDaVisitaEntity.setObjetivo(objetivoDaVisitaDTO.getObjetivo());
                objetivoDaVisitaRepository.save(objetivoDaVisitaEntity);

                response.put("WARN", "Objetivo da visita alterado com sucesso!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Objetivo da visita não encontrado!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao alterar objetivo da visita: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> deleteObjetivoDaVisita(Long id){
        ObjetivoDaVisitaEntity objetivoDaVisitaEntity = objetivoDaVisitaRepository.findById(id).get();
        Map<String, String> response = new HashMap<>();

        try{
            if(objetivoDaVisitaEntity != null){
                objetivoDaVisitaRepository.delete(objetivoDaVisitaEntity);
                response.put("WARN", "Objetivo da visita removido com sucesso!!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Objetivo da visita não encontrado!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao deletar objetivo da visita: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

