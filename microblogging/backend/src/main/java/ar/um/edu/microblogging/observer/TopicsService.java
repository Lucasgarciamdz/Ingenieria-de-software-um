package ar.um.edu.microblogging.observer;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.repositories.EtiquetaRepository;
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

  @Autowired
  public TopicsService(JavaMailSender mailSender, EtiquetaRepository etiquetaRepository) {
    this.mailSender = mailSender;
    this.etiquetaRepository = etiquetaRepository;
    this.subject = new Subject<>();
  }

  public void registerEmail(String email) {
    subject.registerObserver(new EmailObserver(mailSender, email));
  }

  @Scheduled(cron = "0 0 0 * * MON")
  public void updateTopics() {
    Set<Etiqueta> topics = fetchTrendingTopics();
    subject.setTopics(topics);
  }

  private Set<Etiqueta> fetchTrendingTopics() {
    List<Object[]> topEtiquetas = etiquetaRepository.findTop5Etiquetas("1 WEEK");
    Set<Etiqueta> etiquetas = new HashSet<>();
    for (Object[] result : topEtiquetas) {
      Etiqueta etiqueta = (Etiqueta) result[0];
      etiquetas.add(etiqueta);
    }
    return etiquetas;
  }
}
