package lbty.giraturnos.back.GiraTurnosAPI.controller;

import jakarta.validation.Valid;
import lbty.giraturnos.back.GiraTurnosAPI.dto.UsuarioDTO;
import lbty.giraturnos.back.GiraTurnosAPI.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listAllUsuario")
    public List<UsuarioDTO> listAll() {
        return usuarioService.listAll();
    }

    @PostMapping("/newUsuario")
    public ResponseEntity<Map<String, String>> insertUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.insertUsuario(usuarioDTO);
    }

    @PutMapping("/updateUsuario/{id}")
    public ResponseEntity<Map<String, String>> updateUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id) {
        return usuarioService.updateUsuario(usuarioDTO, id);
    }

    @DeleteMapping("/deleteUsuario/{id}")
    public ResponseEntity<Map<String, String>> deleteUsuario(@PathVariable Long id) {
        return usuarioService.deleteUsuario(id);
    }
}

