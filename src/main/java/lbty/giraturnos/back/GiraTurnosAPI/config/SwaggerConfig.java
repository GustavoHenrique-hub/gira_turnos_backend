package lbty.giraturnos.back.GiraTurnosAPI.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GiraTurnos API")
                        .version("1.0.0")
                        .description("Documentação da API para gerenciamento de turnos")
                        .contact(new Contact()
                                .name("Time de Suporte")
                                .email("suporte@giraturnos.com")));
    }
}
