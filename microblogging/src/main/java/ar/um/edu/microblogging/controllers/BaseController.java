package ar.um.edu.microblogging.controllers;


import ar.um.edu.microblogging.dto.responses.BaseResponse;
import java.util.List;

public interface BaseController<T> {
  
  BaseResponse<T> get(Long id);
  
  List<BaseResponse<T>> getAll();
  
  BaseResponse<T> post(T body);
  
  BaseResponse<T> put(Long id, T modificacion);
  
  BaseResponse<String> delete(Long id);
  
}
