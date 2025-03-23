package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.ObjetivoDaVisitaDTO;
import lbty.giraturnos.back.GiraTurnosAPI.application.usecases.ObjetivoDaVisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/objetivoDaVisita")
@CrossOrigin
public class ObjetivoDaVisitaController {

    @Autowired
    private ObjetivoDaVisitaService objetivoDaVisitaService;

    @GetMapping("/listAllObjetivoDaVisita")
    public List<ObjetivoDaVisitaDTO> listAll() {
        return objetivoDaVisitaService.listAll();
    }

    @PostMapping("/newObjetivoDaVisita")
    public ResponseEntity<Map<String, String>> insertObjetivoDaVisita(@Valid @RequestBody ObjetivoDaVisitaDTO objetivoDaVisitaDTO) {
        return objetivoDaVisitaService.insertObjetivoDaVisita(objetivoDaVisitaDTO);
    }

    @PutMapping("/updateObjetivoDaVisita/{id}")
    public ResponseEntity<Map<String, String>> updateObjetivoDaVisita(@Valid @RequestBody ObjetivoDaVisitaDTO objetivoDaVisitaDTO, @PathVariable Long id) {
        return objetivoDaVisitaService.updateObjetivoDaVisita(objetivoDaVisitaDTO, id);
    }

    @DeleteMapping("/deleteObjetivoDaVisita/{id}")
    public ResponseEntity<Map<String, String>> deleteObjetivoDaVisita(@PathVariable Long id) {
        return objetivoDaVisitaService.deleteObjetivoDaVisita(id);
    }
}

