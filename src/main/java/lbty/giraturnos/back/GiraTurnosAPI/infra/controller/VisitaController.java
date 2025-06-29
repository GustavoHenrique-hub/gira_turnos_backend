package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.VisitaDTO;
import lbty.giraturnos.back.GiraTurnosAPI.application.usecases.VisitaService;
import lbty.giraturnos.back.GiraTurnosAPI.infra.projection.VisitaSummary;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/visita")
@CrossOrigin
public class VisitaController {

    @Autowired
    private VisitaService visitaService;

    @GetMapping("/listAllVisita")
    public List<VisitaDTO> listAll() {
        return visitaService.listAll();
    }

    @GetMapping("/visitaPaginator")
    public ResponseEntity<List<VisitaSummary>> visitaPaginator(
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim){
        List<VisitaSummary> visitas = visitaService.visitaPaginator(inicio, fim);
        return ResponseEntity.ok(visitas);
    }

    @PostMapping("/newVisita")
    public ResponseEntity<Map<String, String>> insertVisita(@Valid @RequestBody VisitaDTO visitaDTO) {
        return visitaService.insertVisita(visitaDTO);
    }

    @PutMapping("/updateVisita/{id}")
    public ResponseEntity<Map<String, String>> updateVisita(@Valid @RequestBody VisitaDTO visitaDTO, @PathVariable Long id) {
        return visitaService.updateVisita(visitaDTO, id);
    }

    @DeleteMapping("/deleteVisita/{id}")
    public ResponseEntity<Map<String, String>> deleteVisita(@PathVariable Long id) {
        return visitaService.deleteVisita(id);
    }
}

