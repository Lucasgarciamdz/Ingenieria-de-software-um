package ar.um.edu.microblogging.services;

import java.util.List;

public interface BaseService<T> {
  
  List<T> getAll();
  
  T getById(Long id);
  
  T save (T entity);
  
  T update ( T entity);
  
  boolean delete(Long id);
  
  
  
  
}
