package lbty.giraturnos.back.GiraTurnosAPI.application.usecases;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.VisitaDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.*;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository.*;
import lbty.giraturnos.back.GiraTurnosAPI.infra.projection.VisitaSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service responsável por operações de Visita.
 */
@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    public List<VisitaDTO> listAll() {
        List<VisitaEntity> visitas = visitaRepository.findAll();
        List<VisitaDTO> dtos = new ArrayList<>();
        for (VisitaEntity v : visitas) {
            dtos.add(new VisitaDTO(v));
        }
        return dtos;
    }

    public List<VisitaSummary> visitaPaginator(String inicio, String fim) {
        return visitaRepository.visitaPaginator(inicio, fim);
    }

    public ResponseEntity<Map<String, String>> insertVisita(VisitaDTO visita){
        VisitaEntity visitaEntity = new VisitaEntity(visita);
        Map<String, String> response = new HashMap<>();
        try {
            visitaRepository.save(visitaEntity);
            response.put("WARN", "Visita inserida com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException ex){
            response.put("ERROR", "Erro ao inserir visita:" + ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> insertAllVisitas(List<VisitaDTO> visita){
        Map<String, String> response = new HashMap<>();
        try{
            List<VisitaEntity> visitas = visita.stream()
                    .map(VisitaEntity::new)
                    .toList();

            visitaRepository.saveAll(visitas);

            response.put("WARN", visita.size() + " visitas cadastradas com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException re){
            response.put("ERROR", "Erro ao cadastrar visitas em lote: " + re.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateVisita(VisitaDTO visita, Long id){
        Map<String, String> response = new HashMap<>();
        Optional<VisitaEntity> visitaEntityOptional = visitaRepository.findById(id);

        try{
            if(visitaEntityOptional.isPresent()){
                VisitaEntity visitaEntity = visitaEntityOptional.get();
                visitaEntity.setTecnico(new TecnicoEntity(visita.getTecnico()));
                visitaEntity.setUnidade(new UnidadeEntity(visita.getUnidade()));
                visitaEntity.setTurno(new TurnoEntity(visita.getTurno()));
                visitaEntity.setEscala(new EscalaEntity(visita.getEscala()));
                visitaEntity.setLocalizacao(new LocalizacaoEntity(visita.getLocalizacao()));
                visitaEntity.setDataHoraInicioVisita(visita.getDataHoraInicioVisita());
                visitaEntity.setDataHoraFimVisita(visita.getDataHoraInicioVisita());
                visitaEntity.setObjetivoDaVisita(visita.getObjetivoDaVisita());
                visitaEntity.setNumCard(visita.getNumCard());

                visitaRepository.save(visitaEntity);

                response.put("WARN", "Visita alterada com sucesso!");
                return ResponseEntity.ok(response);
            } else {
                response.put("ERROR", "Visita não encontrada!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("ERROR", "Erro ao alterar visita: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> deleteVisita(Long id) {
        Map<String, String> response = new HashMap<>();
        Optional<VisitaEntity> opt = visitaRepository.findById(id);
        if (opt.isEmpty()) {
            response.put("WARN", "Visita não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try {
            visitaRepository.delete(opt.get());
            response.put("WARN", "Visita removida com sucesso!!");
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            response.put("ERROR", "Erro ao deletar visita: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
