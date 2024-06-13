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
import org.springframework.web.bind.annotation.*;

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
    Usuario usuario1 = usuarioService.getById(id);
    return new BaseResponse<>("Usuariooooo", usuario1);
  }

  @Override
  @GetMapping
  public List<BaseResponse<Usuario>> getAll() {
    List<Usuario> usuarios = usuarioService.getAll();
    return usuarios.stream()
                   .map(usuario -> new BaseResponse<Usuario>("Usuario", usuario))
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
    Usuario usuarioExistente = usuarioService.getById(id);
    if (usuarioExistente == null) {
      return new BaseResponse<>("Usuario no encontrado", null);
    }

    // Actualizar solo los campos que no son nulos
    if (modificacion.getNombreUsuario() != null) {
      usuarioExistente.setNombreUsuario(modificacion.getNombreUsuario());
    }
    if (modificacion.getEmail() != null) {
      usuarioExistente.setEmail(modificacion.getEmail());
    }
    if (modificacion.getFoto() != null) {
      usuarioExistente.setFoto(modificacion.getFoto());
    }
    if (modificacion.getClave() != null) {
      usuarioExistente.setClave(modificacion.getClave());
    }
    if (modificacion.getNombreCompleto() != null) {
      usuarioExistente.setNombreCompleto(modificacion.getNombreCompleto());
    }
    if (modificacion.getDescripcion() != null) {
      usuarioExistente.setDescripcion(modificacion.getDescripcion());
    }
    // No actualizamos los campos de relaciones (mensajes, seguidores, seguidos, mensajesRepublicados)

    Usuario usuarioActualizado = usuarioService.update(usuarioExistente);
    return new BaseResponse<>("Usuario", usuarioActualizado);
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
