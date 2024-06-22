package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.dtos.UsuarioDto;
import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.UsuarioService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
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
public class UsuarioController implements BaseController<Usuario, UsuarioDto> {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @Override
  @GetMapping("/{id}")
  public BaseResponse<Usuario> get(@PathVariable Long id) {
    Usuario usuario1 = usuarioService.getById(id);
    return new BaseResponse<>("Usuariooooo", usuario1);
  }

  @Override
  @GetMapping
  public BaseResponse<List<Usuario>> getAll() {
    List<Usuario> usuarios = usuarioService.getAll();
    return new BaseResponse<>("Usuarios", usuarios);
  }

  @Override
  @PostMapping()
  public BaseResponse<Usuario> post(@RequestBody UsuarioDto body) {
    Usuario usuario = usuarioService.save(body);
    return new BaseResponse<>("Usuario creado con exito", usuario);
  }

  @Override
  @PutMapping("/{id}")
  public BaseResponse<Usuario> put(@PathVariable Long id, @RequestBody UsuarioDto modificacion) {
    Usuario usuarioActualizado = usuarioService.update(id, modificacion);
    return new BaseResponse<>("Usuario", usuarioActualizado);
  }

  @Override
  @DeleteMapping("/{id}")
  public BaseResponse<String> delete(@PathVariable Long id) {
    boolean deleted = usuarioService.delete(id);
    return new BaseResponse<>("Usuario", deleted ? "Deleted" : "Error deleting");
  }

  @PostMapping("/login")
  public BaseResponse<Usuario> login(@RequestParam String email, @RequestParam String clave) {
    Usuario usuario = usuarioService.login(email, clave);
    return new BaseResponse<>("Usuario", usuario);
  }
}
