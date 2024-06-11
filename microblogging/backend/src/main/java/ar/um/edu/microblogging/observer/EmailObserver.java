package ar.um.edu.microblogging.observer;
import ar.um.edu.microblogging.dto.entities.Etiqueta;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.util.Set;
import java.util.stream.Collectors;

public class EmailObserver implements Observer<Etiqueta> {
    private final JavaMailSender mailSender;
    private final String email;

    public EmailObserver(JavaMailSender mailSender, String email) {
        this.mailSender = mailSender;
        this.email = email;
    }

    @Override
    public void update(Set<Etiqueta> topics) {
        sendEmail(topics);
    }

    private void sendEmail(Set<Etiqueta> topics) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        String topicsString = topics.stream()
                .map(Etiqueta::toString)
                .collect(Collectors.joining(", "));

        try {
            helper.setTo(email);
            helper.setSubject("Temas del momento");
            helper.setText(topicsString);
            mailSender.send(mimeMessage);

        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
