package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.UsuarioService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements BaseController<Usuario> {
  
  private final UsuarioService usuarioService;
  
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @Override
  @GetMapping("/{id}")
  public BaseResponse<Usuario> get(@PathVariable Long id) {
    return new BaseResponse<>("Se encontro un usuario", usuarioService.getById(id));
  }
  
  @Override
  @GetMapping
  public List<BaseResponse<Usuario>> getAll() {
    return List.of();
  }

  @Override
  @PostMapping
  public BaseResponse<Usuario> post(@RequestBody Usuario body) {
    return null;
  }

  @Override
  @PutMapping("/{id}")
  public BaseResponse<Usuario> put(@PathVariable Long id, @RequestBody Usuario modificacion) {
    return null;
  }

  @Override
  @DeleteMapping("/{id}")
  public BaseResponse<String> delete(@PathVariable Long id) {
    return null;
  }


}
