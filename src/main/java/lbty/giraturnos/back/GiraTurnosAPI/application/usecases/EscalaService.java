package lbty.giraturnos.back.GiraTurnosAPI.application.usecases;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.EscalaDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.EscalaEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository.EscalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscalaService {

    @Autowired
    private EscalaRepository escalaRepository;

    public List<EscalaDTO> listAll(){
        List<EscalaEntity> escala = escalaRepository.findAll();
        return escala.stream().map(EscalaDTO::new).toList();
    }
}
