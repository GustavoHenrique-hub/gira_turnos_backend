package lbty.giraturnos.back.GiraTurnosAPI.application.usecases;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.VisitaDTO;
import lbty.giraturnos.back.GiraTurnosAPI.entity.*;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    public List<VisitaDTO> listAll(){
        List<VisitaEntity> visita = visitaRepository.findAll();
        return visita.stream().map(VisitaDTO::new).toList();
    }

    public ResponseEntity<Map<String, String>> insertVisita(VisitaDTO visitaDTO){
        VisitaEntity visitaEntity = new VisitaEntity(visitaDTO);
        Map<String, String> response = new HashMap<>();

        try{
            visitaRepository.save(visitaEntity);
            response.put("WARN", "Visita cadastrada com sucesso!!");
            return ResponseEntity.ok(response);
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao cadastrar visita: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateVisita(VisitaDTO visitaDTO, Long id){
        Map<String, String> response = new HashMap<>();
        Optional<VisitaEntity> visitaEntityOptional = visitaRepository.findById(id);

        try{
            if(visitaEntityOptional.isPresent()){
                VisitaEntity visitaEntity = visitaEntityOptional.get();
                visitaEntity.setTecnico(new TecnicoEntity(visitaDTO.getTecnico()));
                visitaEntity.setUnidade(new UnidadeEntity(visitaDTO.getUnidade()));
                visitaEntity.setObjetivoDaVisita(new ObjetivoDaVisitaEntity(visitaDTO.getObjetivoDaVisita()));
                visitaEntity.setTurno(new TurnoEntity(visitaDTO.getTurno()));
                visitaEntity.setResposavelRegistro(new UsuarioEntity(visitaDTO.getResposavelRegistro()));
                visitaEntity.setDataDaVisita(visitaDTO.getDataDaVisita());
                visitaEntity.setHorarioInicioVisita(visitaDTO.getHorarioInicioVisita());
                visitaEntity.setHorarioFimVisita(visitaDTO.getHorarioFimVisita());
                visitaEntity.setDataHoraRegistro(visitaDTO.getDataHoraRegistro());
                visitaRepository.save(visitaEntity);

                response.put("WARN", "Visita alterada com sucesso!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Visita não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao alterar visita: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> deleteVisita(Long id){
        VisitaEntity visitaEntity = visitaRepository.findById(id).get();
        Map<String, String> response = new HashMap<>();

        try{
            if(visitaEntity != null){
                visitaRepository.delete(visitaEntity);
                response.put("WARN", "Visita removida com sucesso!!");
                return ResponseEntity.ok(response);
            }else {
                response.put("WARN", "Visita não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (RuntimeException re){
            response.put("ERROR", "Erro ao deletar visita: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

