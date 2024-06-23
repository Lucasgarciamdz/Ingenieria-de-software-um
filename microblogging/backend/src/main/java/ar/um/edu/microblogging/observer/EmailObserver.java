package ar.um.edu.microblogging.observer;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailObserver implements Observer<Etiqueta> {
  private final JavaMailSender mailSender;
  private final String email;

  public EmailObserver(JavaMailSender mailSender, String email) {
    this.mailSender = mailSender;
    this.email = email;
  }

  @Override
  public void update(List<Etiqueta> topics) throws MessagingException {
    sendEmail(topics);
  }

  private void sendEmail(List<Etiqueta> topics) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

    String topicsString = topics.stream().map(Etiqueta::toString).collect(Collectors.joining(", "));

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
