package vanthan99.harmic.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@RestController
@RequestMapping("api/v1/mail")
@CrossOrigin
@Api
public class MailController {
    @Autowired
    public JavaMailSender mailSender;

    @ResponseBody
    @PostMapping(value = "/create/{emailReceive}")
    public ResponseEntity<Object> sendEmailCreateAccount(@PathVariable String emailReceive) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        String htmlMsg = "<h3>Hi!</h3>"
                +"<h3>Welcome to Raovat. Verify your account please <a href='http://localhost:8080/confirmAccount' >click here</a></h3>";
        message.setContent(htmlMsg, "text/html");
        helper.setTo(emailReceive);
        helper.setSubject("Welcome to Raovat");

        this.mailSender.send(message);

        return ResponseEntity.ok("Email Sent!");
    }

    @ResponseBody
    @PostMapping("/getpw/{emailReceive}")
    public ResponseEntity<Object> sendEmailGetPassword(@PathVariable String emailReceive) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        String newPassword = randomPassword();
        String htmlMsg = "<h3>Hi!</h3>"
                +"<h3>Welcome to Raovat. This is your new password:</h3>"
                +"<h1>"+newPassword+"</h1>"
                +"<h3>Confirm your new password please <a href='http://localhost:8080/api/v1/auth/changePasswordByEmail/"+emailReceive+"/"+newPassword+"'>click here</a></h3>";
        message.setContent(htmlMsg, "text/html");
        helper.setTo(emailReceive);
        helper.setSubject("Get Password Raovat");

        this.mailSender.send(message);

        return ResponseEntity.ok("Email Sent!");
    }

    private String randomPassword() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
