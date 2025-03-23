package lbty.giraturnos.back.GiraTurnosAPI.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.LocalizacaoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.service.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/localizacao")
@CrossOrigin
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping("/listAllLocalizacao")
    public List<LocalizacaoDTO> listAll() {
        return localizacaoService.listAll();
    }

    @PostMapping("/newLocalizacao")
    public ResponseEntity<Map<String, String>> insertLocalizacao(@Valid @RequestBody LocalizacaoDTO localizacaoDTO) {
        return localizacaoService.insertLocalizacao(localizacaoDTO);
    }

    @PutMapping("/updateLocalizacao/{id}")
    public ResponseEntity<Map<String, String>> updateLocalizacao(@Valid @RequestBody LocalizacaoDTO localizacaoDTO, @PathVariable Long id) {
        return localizacaoService.updateLocalizacao(localizacaoDTO, id);
    }

    @DeleteMapping("/deleteLocalizacao/{id}")
    public ResponseEntity<Map<String, String>> deleteLocalizacao(@PathVariable Long id) {
        return localizacaoService.deleteLocalizacao(id);
    }
}
