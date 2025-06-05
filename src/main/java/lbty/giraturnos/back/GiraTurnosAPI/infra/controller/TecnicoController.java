package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.TecnicoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.application.usecases.TecnicoService;
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
    @Operation(summary = "Listar todos os técnicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Técnicos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TecnicoDTO.class))
                    ),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)})
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
