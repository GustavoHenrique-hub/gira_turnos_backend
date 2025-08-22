package lbty.giraturnos.back.GiraTurnosAPI.infra.controller;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import io.github.cdimascio.dotenv.Dotenv;
import lbty.giraturnos.back.GiraTurnosAPI.infra.percistence.jpa.entity.EmailEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:5173/")
public class EmailController {
    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendEmail(@RequestBody EmailEntity emailRequest) {
        // Defina sua chave da API do SendGrid
        Dotenv dotenv = Dotenv.configure().directory(".").load();
        String sendGridApiKey = dotenv.get("SENDGRID_API_KEY"); // Substitua pela sua chave real da API



        // Cria os objetos de e-mail
        Email from = new Email("operacoes@libertyti.com.br"); // Defina o seu e-mail de envio
        Email to = new Email(emailRequest.getTo());
        Content content = new Content("text/html", emailRequest.getHtml());
        Mail mail = new Mail(from, emailRequest.getSubject(), to, content);

        // Cria a instância do SendGrid
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        Map<String, String> responseMessage = new HashMap<>();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            if (response.getStatusCode() == 202 || response.getStatusCode() == 200) {
                responseMessage.put("WARN", "Email enviado com sucesso!");
                return ResponseEntity.status(response.getStatusCode()).body(responseMessage);
            } else {
                responseMessage.put("ERROR", "Erro ao enviar e-mail: " + response.getBody());
                return ResponseEntity.status(response.getStatusCode()).body(responseMessage);
            }
        } catch (IOException e) {
            responseMessage.put("ERROR", "Erro ao enviar e-mail: " + e.getMessage());
            return ResponseEntity.status(500).body(responseMessage);
        }
    }
}
