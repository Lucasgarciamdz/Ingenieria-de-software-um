package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.UsuarioService;
import java.util.List;
import java.util.stream.Collectors;

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
    Usuario usuario = usuarioService.getById(id);
    return new BaseResponse<>("Usuario", usuario);
  }

  @Override
  @GetMapping
  public List<BaseResponse<Usuario>> getAll() {
    List<Usuario> usuarios = usuarioService.getAll();
    return usuarios.stream()
                   .map(usuario -> new BaseResponse<>("Usuario", usuario))
                   .collect(Collectors.toList());
  }

  @Override
  @PostMapping
  public BaseResponse<Usuario> post(@RequestBody Usuario body) {
    Usuario usuario = usuarioService.save(body);
    return new BaseResponse<>("Usuario", usuario);
  }

  @Override
  @PutMapping("/{id}")
  public BaseResponse<Usuario> put(@PathVariable Long id, @RequestBody Usuario modificacion) {
    modificacion.setId(id);
    Usuario usuario = usuarioService.update(modificacion);
    return new BaseResponse<>("Usuario", usuario);
  }

  @Override
  @DeleteMapping("/{id}")
  public BaseResponse<String> delete(@PathVariable Long id) {
    boolean deleted = usuarioService.delete(id);
    return new BaseResponse<>("Usuario", deleted ? "Deleted" : "Error deleting");
  }

  @PostMapping("/login")
  public BaseResponse<Usuario> login(@RequestBody String email, @RequestBody String clave) {
    Usuario usuario = usuarioService.login(email, clave);
    return new BaseResponse<>("Usuario", usuario);
  }
}
