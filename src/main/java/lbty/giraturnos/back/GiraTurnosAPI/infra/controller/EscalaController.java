package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import lbty.giraturnos.back.GiraTurnosAPI.application.usecases.EscalaService;
import lbty.giraturnos.back.GiraTurnosAPI.application.usecases.LocalizacaoService;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.EscalaDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.LocalizacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/escala")
@CrossOrigin
public class EscalaController {

    @Autowired
    private EscalaService escalaService;

    @GetMapping("/listAllEscala")
    public List<EscalaDTO> listAll() {
        return escalaService.listAll();
    }
}
