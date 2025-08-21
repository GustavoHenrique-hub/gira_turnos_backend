package lbty.giraturnos.back.GiraTurnosAPI.application.usecases;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.TurnoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.TurnoEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public List<TurnoDTO> listAll(){
        List<TurnoEntity> turno = turnoRepository.findAllOrderedByName();
        return turno.stream().map(TurnoDTO::new).toList();
    }
}

