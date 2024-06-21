package ar.um.edu.microblogging.observer;

import java.util.Set;

public interface Observer<T> {
  void update(Set<T> topics);
}
