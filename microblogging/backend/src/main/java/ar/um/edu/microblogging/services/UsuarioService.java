package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.dtos.UsuarioDto;
import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.dto.requests.NuevoUsuarioDto;
import ar.um.edu.microblogging.repositories.UsuarioRepository;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements BaseService<Usuario> {

  private final UsuarioRepository usuarioRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UsuarioService(
      UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
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

  
  public Usuario saveDto(UsuarioDto dto) {

      Usuario entity = new Usuario();
      BeanUtils.copyProperties(dto, entity);
      entity.setClave(passwordEncoder.encode(entity.getClave()));
      return this.usuarioRepository.save(entity);

  }
  
  

  @Override
  public Usuario update(Usuario entity) {
    
    Usuario user = this.usuarioRepository.findById(entity.getId()).orElse(null);

    BeanUtils.copyProperties(dto, entity);
    
    
    if (usuarioRepository.existsById(entity.getId())) {
      return this.usuarioRepository.save(entity);
    } else {
      return null;
    }
  }
  
  public Usuario updateDto(UsuarioDto dto, Long id) {

    Usuario entity = this.usuarioRepository.findById(id).orElse(null);
    assert entity != null;
    BeanUtils.copyProperties(dto, entity);
    entity.setClave(passwordEncoder.encode(entity.getClave()));
    return this.usuarioRepository.save(entity);
  }

  @Override
  public boolean delete(Long id) {
    return this.usuarioRepository
        .findById(id)
        .map(
            usuario -> {
              this.usuarioRepository.delete(usuario);
              return true;
            })
        .orElse(false);
  }

  public Usuario login(String email, String clave) {
    return usuarioRepository.findByEmailAndClave(email, clave);
  }

  public Usuario registrarNuevoUsuario(NuevoUsuarioDto nuevoUsuarioDto) {
    Usuario usuario = new Usuario();
    usuario.setNombreUsuario(nuevoUsuarioDto.getNombre());
    usuario.setEmail(nuevoUsuarioDto.getEmail());
    usuario.setClave(passwordEncoder.encode(nuevoUsuarioDto.getClave()));
    usuario.setFoto(nuevoUsuarioDto.getFoto());
    usuario.setNombreCompleto(nuevoUsuarioDto.getNombreCompleto());
    usuario.setDescripcion(nuevoUsuarioDto.getDescripcion());
    usuario.setMensajes(nuevoUsuarioDto.getMensajes());
    usuario.setSeguidores(nuevoUsuarioDto.getSeguidores());
    usuario.setSeguidos(nuevoUsuarioDto.getSeguidos());

    return this.usuarioRepository.save(usuario);
  }
}
