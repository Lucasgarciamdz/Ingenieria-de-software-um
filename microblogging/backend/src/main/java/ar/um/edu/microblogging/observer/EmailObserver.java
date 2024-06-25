package ar.um.edu.microblogging.observer;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
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
    // Ensure multipart is true to support inline elements
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

    String appName = "Microblogging";
    String cid = "logo"; // Unique Content-ID

    String htmlContent =
        "<html>"
            + "<head>"
            + "<style>"
            + "  body { font-family: Arial, sans-serif; text-align: center; margin: 0 auto; max-width: 600px; }"
            + "  h1 { color: #333366; }"
            + "  ul { list-style-type: none; padding: 0; }"
            + "  li { background-color: #f2f2f2; margin: 5px 0; padding: 10px; border-radius: 5px; text-align: left; }"
            + "  .header, .footer { padding: 20px; background-color: #f8f8f8; }"
            + "  .footer { font-size: 0.8em; }"
            + "</style>"
            + "</head>"
            + "<body>"
            + "<div class='header'>"
            // Replace 'DIRECT_IMAGE_URL_HERE' with the actual direct URL to your Imgur image
            + "  <img src='https://i.imgur.com/SEgR2L8.png' alt='App Logo' style='max-width: 100px;'>"
            + "  <h1>"
            + appName
            + "</h1>"
            + "<h2> Temas del momento </h2>"
            + "</div>"
            + "<ul>"
            + topics.stream()
            .map(etiqueta -> "<li>" + etiqueta.getNombre() + "</li>")
            .collect(Collectors.joining(" "))
            + "</ul>"
            + "<div class='footer'>"
            + "  © "
            + java.time.Year.now()
            + " "
            + appName
            + ". All rights reserved."
            + "</div>"
            + "</body>"
            + "</html>";

    // Attach the image and ensure the path is correct
    helper.addInline(cid, new ClassPathResource("logo.png"), "image/png");

    try {
      helper.setTo(email);
      helper.setSubject("Temas del momento");
      helper.setText(htmlContent, true); // true indicates HTML content
      mailSender.send(mimeMessage);
    } catch (jakarta.mail.MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}
