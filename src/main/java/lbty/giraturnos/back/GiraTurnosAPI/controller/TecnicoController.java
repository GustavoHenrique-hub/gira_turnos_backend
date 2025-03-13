package lbty.giraturnos.back.GiraTurnosAPI.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.dto.TecnicoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tecnico")
@CrossOrigin
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping("/listAllTecnico")
    public List<TecnicoDTO> listAll(){
        return tecnicoService.listAll();
    }

    @PostMapping("/newTecnico")
    public ResponseEntity<Map<String, String>> insertTecnico(@Valid @RequestBody TecnicoDTO tecnicoDTO){
        return tecnicoService.insertTecnico(tecnicoDTO);
    }

    @PutMapping("/updateTecnico/{id}")
    public ResponseEntity<Map<String, String>> updateTecnico(@Valid @RequestBody TecnicoDTO tecnicoDTO, @PathVariable Long id){
        return tecnicoService.updateTecnico(tecnicoDTO, id);
    }

    @DeleteMapping("/deleteTecnico/{id}")
    public ResponseEntity<Map<String, String>> deleteTecnico(@PathVariable Long id){
        return tecnicoService.deleteTecnico(id);
    }
}
