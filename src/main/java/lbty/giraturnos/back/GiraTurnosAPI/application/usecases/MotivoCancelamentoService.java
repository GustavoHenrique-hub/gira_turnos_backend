package lbty.giraturnos.back.GiraTurnosAPI.application.usecases;

import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.EscalaDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.MotivoCancelamentoDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.EscalaEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.MotivoCancelamentoEntity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.repository.MotivoCancelamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotivoCancelamentoService {

    @Autowired
    private MotivoCancelamentoRepository motivoCancelamentoRepository;

    public List<MotivoCancelamentoDTO> listAll(){
        List<MotivoCancelamentoEntity> motivoCancelamento = motivoCancelamentoRepository.findAll();
        return motivoCancelamento.stream().map(MotivoCancelamentoDTO::new).toList();
    }
}
