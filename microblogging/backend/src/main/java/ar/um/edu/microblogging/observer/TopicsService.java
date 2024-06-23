package ar.um.edu.microblogging.observer;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.repositories.EtiquetaRepository;
import ar.um.edu.microblogging.services.EtiquetaService;
import jakarta.mail.MessagingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TopicsService {
  private final Subject<Etiqueta> subject;
  private final JavaMailSender mailSender;
  private final EtiquetaRepository etiquetaRepository;
  private final EtiquetaService etiquetaService;

  @Autowired
  public TopicsService(JavaMailSender mailSender, EtiquetaRepository etiquetaRepository, EtiquetaService etiquetaService) {
    this.mailSender = mailSender;
    this.etiquetaRepository = etiquetaRepository;
    this.etiquetaService = etiquetaService;
    this.subject = new Subject<>();
  }

  public void registerEmail(String email) {
    subject.registerObserver(new EmailObserver(mailSender, email));
  }

  @Scheduled(fixedRate = 10000)
  public void updateTopics() throws MessagingException {
    List<Etiqueta> topics = fetchTrendingTopics();
    subject.setTopics(topics);
  }

  private List<Etiqueta> fetchTrendingTopics() {
    List<Etiqueta> topEtiquetas = etiquetaService.getTemasMomento(5);
    return topEtiquetas;
  }
}
