package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import lbty.giraturnos.back.GiraTurnosAPI.application.usecases.MotivoCancelamentoService;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.MotivoCancelamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/motivoCancelamento")
@CrossOrigin
public class MotivoCancelamentoController {

    @Autowired
    private MotivoCancelamentoService motivoCancelamentoService;

    @GetMapping("/listAllMotivoCancelamento")
    public List<MotivoCancelamentoDTO> listAll(){
        return motivoCancelamentoService.listAll();
    }
}
