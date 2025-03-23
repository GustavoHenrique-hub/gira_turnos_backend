package lbty.giraturnos.back.GiraTurnosAPI.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.dto.GestaoPorUnidadeDTO;
import lbty.giraturnos.back.GiraTurnosAPI.service.GestaoPorUnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gestaoPorUnidade")
@CrossOrigin
public class GestaoPorUnidadeController {

    @Autowired
    private GestaoPorUnidadeService gestaoPorUnidadeService;

    @GetMapping("/listAllGestaoPorUnidade")
    public List<GestaoPorUnidadeDTO> listAll() {
        return gestaoPorUnidadeService.listAll();
    }

    @PostMapping("/newGestaoPorUnidade")
    public ResponseEntity<Map<String, String>> insertGestaoPorUnidade(@Valid @RequestBody GestaoPorUnidadeDTO gestaoPorUnidadeDTO) {
        return gestaoPorUnidadeService.insertGestaoPorUnidade(gestaoPorUnidadeDTO);
    }

    @PutMapping("/updateGestaoPorUnidade/{id}")
    public ResponseEntity<Map<String, String>> updateGestaoPorUnidade(@Valid @RequestBody GestaoPorUnidadeDTO gestaoPorUnidadeDTO, @PathVariable Long id) {
        return gestaoPorUnidadeService.updateGestaoPorUnidade(gestaoPorUnidadeDTO, id);
    }

    @DeleteMapping("/deleteGestaoPorUnidade/{id}")
    public ResponseEntity<Map<String, String>> deleteGestaoPorUnidade(@PathVariable Long id) {
        return gestaoPorUnidadeService.deleteGestaoPorUnidade(id);
    }
}

