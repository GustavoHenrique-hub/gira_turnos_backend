package lbty.giraturnos.back.GiraTurnosAPI.infra.dto;

import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.EmailEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class EmailDTO {

    private String to;
    private String subject;
    private String html;
    private String cc;
    private String send_at;

    public EmailDTO(EmailEntity emailEntity) {
        BeanUtils.copyProperties(emailEntity, this);
    }
}
