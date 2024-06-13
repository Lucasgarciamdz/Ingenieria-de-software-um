package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.repositories.UsuarioRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements BaseService<Usuario>{
  
  private final UsuarioRepository usuarioRepository;
  
  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }
  
  @Override
  public List<Usuario> getAll() {
    return this.usuarioRepository.findAll();
  }

  @Override
  public Usuario getById(Long id) {
    return this.usuarioRepository.findById(id).orElse(null);
  }

  @Override
  public Usuario save(Usuario entity) {
    return this.usuarioRepository.save(entity);
  }

  @Override
  public Usuario update(Usuario entity) {
    if (usuarioRepository.existsById(entity.getId())) {
      return this.usuarioRepository.save(entity);
    } else {
      return null; // O lanza una excepción si prefieres manejarlo de otra manera
    }
  }

  @Override
  public boolean delete(Long id) {
    return this.usuarioRepository.findById(id).map(usuario -> {
      this.usuarioRepository.delete(usuario);
      return true;
    }).orElse(false);
  }

  public Usuario login(String email, String clave) {
    return usuarioRepository.findByEmailAndClave(email, clave);
  }
}
