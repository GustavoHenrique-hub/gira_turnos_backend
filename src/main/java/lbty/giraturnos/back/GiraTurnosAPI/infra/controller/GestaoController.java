package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.GestaoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.application.usecases.GestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gestao")
@CrossOrigin
public class GestaoController {

    @Autowired
    private GestaoService gestaoService;

    @GetMapping("/listAllGestao")
    public List<GestaoDTO> listAll() {
        return gestaoService.listAll();
    }

    @PostMapping("/newGestao")
    public ResponseEntity<Map<String, String>> insertGestao(@Valid @RequestBody GestaoDTO gestaoDTO) {
        return gestaoService.insertGestao(gestaoDTO);
    }

    @PutMapping("/updateGestao/{id}")
    public ResponseEntity<Map<String, String>> updateGestao(@Valid @RequestBody GestaoDTO gestaoDTO, @PathVariable Long id) {
        return gestaoService.updateGestao(gestaoDTO, id);
    }

    @DeleteMapping("/deleteGestao/{id}")
    public ResponseEntity<Map<String, String>> deleteGestao(@PathVariable Long id) {
        return gestaoService.deleteGestao(id);
    }
}

