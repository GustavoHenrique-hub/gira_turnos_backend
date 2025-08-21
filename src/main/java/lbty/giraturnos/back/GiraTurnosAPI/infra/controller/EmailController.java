package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.EmailEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:5173/")
public class EmailController {

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailEntity emailRequest) {
        // Defina sua chave da API do SendGrid
        String sendGridApiKey = ""; // Substitua pela sua chave real da API

        // Cria os objetos de e-mail
        Email from = new Email("gustavosilva.h37@gmail.com"); // Defina o seu e-mail de envio
        Email to = new Email(emailRequest.getTo());
        Content content = new Content("text/html", emailRequest.getHtml());
        Mail mail = new Mail(from, emailRequest.getSubject(), to, content);

        // Cria a instância do SendGrid
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            if (response.getStatusCode() == 202) {
                return ResponseEntity.ok().body("Email enviado com sucesso!");
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Erro ao enviar e-mail: " + response.getBody());
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}
