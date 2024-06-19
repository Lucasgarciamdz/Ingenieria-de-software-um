package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.responses.BaseResponse;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BaseController<T> {

  BaseResponse<T> get(Long id);

  BaseResponse<List<T>> getAll();

  BaseResponse<T> post(T body);

  BaseResponse<T> put(Long id, T modificacion);

  BaseResponse<String> delete(Long id);
}
