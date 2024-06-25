package ar.um.edu.microblogging.observer;

import jakarta.mail.MessagingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subject<T> {
  private final Set<Observer<T>> observers = new HashSet<>();
  private List<T> topics;

  public Subject() {}

  public void registerObserver(Observer<T> observer) {
    observers.add(observer);
  }

  public void unregisterObserver(Observer<T> observer) {
    observers.remove(observer);
  }

  public void notifyObservers() throws MessagingException {
    for (Observer<T> observer : observers) {
      observer.update(topics);
    }
  }

  public void setTopics(List<T> topics) throws MessagingException {
    this.topics = topics;
    notifyObservers();
  }
}
