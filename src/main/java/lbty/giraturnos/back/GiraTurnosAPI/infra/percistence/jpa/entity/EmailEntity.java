package lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity;

import jakarta.persistence.Entity;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.EmailDTO;
import lbty.giraturnos.back.GiraTurnosAPI.infra.dto.EscalaDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class EmailEntity {
    private String to;
    private String subject;
    private String html;
    private String cc;
    private String send_at;

    public EmailEntity(EmailDTO emailDTO) {
        BeanUtils.copyProperties(emailDTO, this);
    }
}
