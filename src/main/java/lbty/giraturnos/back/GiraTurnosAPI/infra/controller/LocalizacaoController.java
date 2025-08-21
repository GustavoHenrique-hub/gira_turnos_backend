package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.LocalizacaoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.application.usecases.LocalizacaoService;
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
}
