package ar.um.edu.microblogging.observer;

import jakarta.mail.MessagingException;
import java.util.List;
import java.util.Set;

public interface Observer<T> {
  void update(List<T> topics) throws MessagingException;
}
