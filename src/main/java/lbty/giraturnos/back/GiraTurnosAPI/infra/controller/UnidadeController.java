package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.UnidadeDTO;
import lbty.giraturnos.back.GiraTurnosAPI.service.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unidade")
@CrossOrigin
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @GetMapping("/listAllUnidade")
    public List<UnidadeDTO> listAll() {
        return unidadeService.listAll();
    }

    @PostMapping("/newUnidade")
    public ResponseEntity<Map<String, String>> insertUnidade(@Valid @RequestBody UnidadeDTO unidadeDTO) {
        return unidadeService.insertUnidade(unidadeDTO);
    }

    @PutMapping("/updateUnidade/{id}")
    public ResponseEntity<Map<String, String>> updateUnidade(@Valid @RequestBody UnidadeDTO unidadeDTO, @PathVariable Long id) {
        return unidadeService.updateUnidade(unidadeDTO, id);
    }

    @DeleteMapping("/deleteUnidade/{id}")
    public ResponseEntity<Map<String, String>> deleteUnidade(@PathVariable Long id) {
        return unidadeService.deleteUnidade(id);
    }
}

