package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.TurnoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.application.usecases.TurnoService;
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
}

