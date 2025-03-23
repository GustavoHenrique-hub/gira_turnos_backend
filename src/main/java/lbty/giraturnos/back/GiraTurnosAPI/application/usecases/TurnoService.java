package lbty.giraturnos.back.GiraTurnosAPI.application.usecases;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.TurnoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.TurnoEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public List<TurnoDTO> listAll(){
        List<TurnoEntity> turno = turnoRepository.findAllOrderedByName();
        return turno.stream().map(TurnoDTO::new).toList();
    }

    public ResponseEntity<Map<String, String>> insertTurno(TurnoDTO turnoDTO){
        TurnoEntity turnoEntity = new TurnoEntity(turnoDTO);
        Map<String, String> response = new HashMap<>();

        try{
            turnoRepository.save(turnoEntity);
            response.put("WARN", "Turno cadastrado com sucesso!!");
            return ResponseEntity.ok(response);
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao cadastrar turno: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateTurno(TurnoDTO turnoDTO, Long id){
        Map<String, String> response = new HashMap<>();
        Optional<TurnoEntity> turnoEntityOptional = turnoRepository.findById(id);

        try{
            if(turnoEntityOptional.isPresent()){
                TurnoEntity turnoEntity = turnoEntityOptional.get();
                turnoEntity.setTurno(turnoDTO.getTurno());
                turnoRepository.save(turnoEntity);

                response.put("WARN", "Turno alterado com sucesso!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Turno não encontrado!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao alterar turno: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> deleteTurno(Long id){
        TurnoEntity turnoEntity = turnoRepository.findById(id).get();
        Map<String, String> response = new HashMap<>();

        try{
            if(turnoEntity != null){
                turnoRepository.delete(turnoEntity);
                response.put("WARN", "Turno removido com sucesso!!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Turno não encontrado!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao deletar turno: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

