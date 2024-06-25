package ar.um.edu.microblogging.services;

import java.util.List;

public interface BaseService<T, D> {

  List<T> getAll();

  T getById(Long id);

  T save(D dto);

  T update(Long id, D dto);

  boolean delete(Long id);
}
