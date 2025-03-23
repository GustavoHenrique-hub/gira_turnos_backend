package lbty.giraturnos.back.GiraTurnosAPI.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.TurnoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/turno")
@CrossOrigin
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/listAllTurno")
    public List<TurnoDTO> listAll() {
        return turnoService.listAll();
    }

    @PostMapping("/newTurno")
    public ResponseEntity<Map<String, String>> insertTurno(@Valid @RequestBody TurnoDTO turnoDTO) {
        return turnoService.insertTurno(turnoDTO);
    }

    @PutMapping("/updateTurno/{id}")
    public ResponseEntity<Map<String, String>> updateTurno(@Valid @RequestBody TurnoDTO turnoDTO, @PathVariable Long id) {
        return turnoService.updateTurno(turnoDTO, id);
    }

    @DeleteMapping("/deleteTurno/{id}")
    public ResponseEntity<Map<String, String>> deleteTurno(@PathVariable Long id) {
        return turnoService.deleteTurno(id);
    }
}

