package ar.um.edu.microblogging.controllers;


import ar.um.edu.microblogging.dto.responses.BaseResponse;
import java.util.List;

public interface BaseController<T> {
  
  BaseResponse<T> get(Long id);
  
  List<BaseResponse<T>> get_all();
  
  BaseResponse<T> post(Long id, T body);
  
  BaseResponse<T> put(Long id, T modificacion);
  
  BaseResponse<String> delete(Long id);
  
}
