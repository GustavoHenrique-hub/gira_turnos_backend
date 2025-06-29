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

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private EscalaRepository escalaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    /**
     * Insere uma nova visita, garantindo que as referências ManyToOne
     * sejam entidades gerenciadas pelo JPA.
     */
    @Transactional
    public ResponseEntity<Map<String, String>> insertVisita(VisitaDTO dto) {
        Map<String, String> response = new HashMap<>();
        try {
            VisitaEntity entity = new VisitaEntity();

            // Data/Hora
            entity.setDataHoraInicioVisita(dto.getDataHoraInicioVisita());
            entity.setDataHoraFimVisita(dto.getDataHoraFimVisita());
            entity.setDataHoraRegistro(dto.getDataHoraRegistro());
            entity.setObjetivoDaVisita(dto.getObjetivoDaVisita());
            entity.setNumCard(dto.getNumCard());

            // Associação de Tecnico
            TecnicoEntity tec = tecnicoRepository.findById(dto.getTecnico().getId())
                    .orElseThrow(() -> new RuntimeException("Técnico não encontrado: id=" + dto.getTecnico().getId()));
            entity.setTecnico(tec);

            // Associação de Unidade
            UnidadeEntity uni = unidadeRepository.findById(dto.getUnidade().getId())
                    .orElseThrow(() -> new RuntimeException("Unidade não encontrada: id=" + dto.getUnidade().getId()));
            entity.setUnidade(uni);

            // Turno
            TurnoEntity tur = turnoRepository.findById(dto.getTurno().getId())
                    .orElseThrow(() -> new RuntimeException("Turno não encontrado: id=" + dto.getTurno().getId()));
            entity.setTurno(tur);

            // Localização
            LocalizacaoEntity loc = localizacaoRepository.findById(dto.getLocalizacao().getId())
                    .orElseThrow(() -> new RuntimeException("Localização não encontrada: id=" + dto.getLocalizacao().getId()));
            entity.setLocalizacao(loc);

            // Escala
            EscalaEntity esc = escalaRepository.findById(dto.getEscala().getId())
                    .orElseThrow(() -> new RuntimeException("Escala não encontrada: id=" + dto.getEscala().getId()));
            entity.setEscala(esc);

            // Usuário responsável
            if (dto.getResposavelRegistro() != null && dto.getResposavelRegistro().getId() != null) {
                UsuarioEntity usr = usuarioRepository.findById(dto.getResposavelRegistro().getId())
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado: id=" + dto.getResposavelRegistro().getId()));
                entity.setResposavelRegistro(usr);
            }

            visitaRepository.save(entity);

            response.put("WARN", "Visita cadastrada com sucesso!!");
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            response.put("ERROR", "Erro ao cadastrar visita: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Atualiza uma visita existente, recarregando referências gerenciadas.
     */
    @Transactional
    public ResponseEntity<Map<String, String>> updateVisita(VisitaDTO dto, Long id) {
        Map<String, String> response = new HashMap<>();
        Optional<VisitaEntity> opt = visitaRepository.findById(id);
        if (opt.isEmpty()) {
            response.put("WARN", "Visita não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try {
            VisitaEntity entity = opt.get();

            // Atualiza campos simples
            entity.setDataHoraInicioVisita(dto.getDataHoraInicioVisita());
            entity.setDataHoraFimVisita(dto.getDataHoraFimVisita());
            entity.setDataHoraRegistro(dto.getDataHoraRegistro());
            entity.setObjetivoDaVisita(dto.getObjetivoDaVisita());
            entity.setNumCard(dto.getNumCard());

            // Atualiza associações:
            entity.setTecnico(tecnicoRepository.getReferenceById(dto.getTecnico().getId()));
            entity.setUnidade(unidadeRepository.getReferenceById(dto.getUnidade().getId()));
            entity.setTurno(turnoRepository.getReferenceById(dto.getTurno().getId()));
            entity.setLocalizacao(localizacaoRepository.getReferenceById(dto.getLocalizacao().getId()));
            entity.setEscala(escalaRepository.getReferenceById(dto.getEscala().getId()));
            if (dto.getResposavelRegistro() != null) {
                entity.setResposavelRegistro(usuarioRepository.getReferenceById(dto.getResposavelRegistro().getId()));
            }

            visitaRepository.save(entity);

            response.put("WARN", "Visita alterada com sucesso!");
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            response.put("ERROR", "Erro ao alterar visita: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Deleta a visita pelo ID.
     */
    @Transactional
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
