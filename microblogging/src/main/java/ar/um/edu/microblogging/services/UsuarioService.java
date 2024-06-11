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
    return List.of();
  }

  @Override
  public Usuario getById(Long id) {
    return null;
  }

  @Override
  public Usuario save(Usuario entity) {
    return null;
  }

  @Override
  public Usuario update(Usuario entity) {
    return null;
  }

  @Override
  public boolean delete(Long id) {
    return false;
  }
}
