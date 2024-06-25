package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.responses.BaseResponse;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BaseController<T, D> {

  BaseResponse<T> get(Long id);

  BaseResponse<List<T>> getAll();

  BaseResponse<T> post(D body);

  BaseResponse<T> put(Long id, D modificacion);

  BaseResponse<String> delete(Long id);
}
