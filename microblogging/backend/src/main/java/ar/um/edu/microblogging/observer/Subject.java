package ar.um.edu.microblogging.observer;

import java.util.HashSet;
import java.util.Set;

public class Subject<T> {
  private final Set<Observer<T>> observers = new HashSet<>();
  private Set<T> topics;

  public Subject() {}

  public void registerObserver(Observer<T> observer) {
    observers.add(observer);
  }

  public void unregisterObserver(Observer<T> observer) {
    observers.remove(observer);
  }

  public void notifyObservers() {
    for (Observer<T> observer : observers) {
      observer.update(topics);
    }
  }

  public void setTopics(Set<T> topics) {
    this.topics = topics;
    notifyObservers();
  }
}
