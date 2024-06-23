package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.dtos.UsuarioDto;
import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.dto.requests.FollowUserDto;
import ar.um.edu.microblogging.repositories.UsuarioRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends DtoMapper implements BaseService<Usuario, UsuarioDto> {

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
  public Usuario save(UsuarioDto dto) {

    Usuario entity = new Usuario();
    copyNonNullProperties(dto, entity);
    entity.setClave(passwordEncoder.encode(entity.getClave()));
    return this.usuarioRepository.save(entity);
  }

  @Override
  public Usuario update(Long id, UsuarioDto dto) {

    Usuario entity = this.usuarioRepository.findById(id).orElse(null);
    assert entity != null;
    copyNonNullProperties(dto, entity);
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
    Usuario usuario = usuarioRepository.findByEmail(email);
    if (usuario != null && passwordEncoder.matches(clave, usuario.getClave())) {
      return usuario;
    }
    return null;
  }

  public Usuario follow(FollowUserDto followUserDto) throws Exception {
    Optional<Usuario> optionalUsuario = usuarioRepository.findById(followUserDto.idUsuario());
    Optional<Usuario> optionalUsuarioFollow = usuarioRepository.findById(followUserDto.idSeguir());

    if (optionalUsuario.isPresent() && optionalUsuarioFollow.isPresent()) {
      Usuario usuario = optionalUsuario.get();
      Usuario usuarioFollow = optionalUsuarioFollow.get();

      usuario.getSeguidos().add(usuarioFollow);
      usuarioFollow.getSeguidores().add(usuario);

      usuarioRepository.save(usuario);
      usuarioRepository.save(usuarioFollow);

      return usuario;

    } else {
      throw new Exception("No existe perro, fijate logi");
    }
  }

  public List<Usuario> getUsuariosByNombre(Long idUsuario, String nombreUsuario) {
    Optional<Usuario> requestingUserOpt = usuarioRepository.findById(idUsuario);

    if (requestingUserOpt.isEmpty()) {
      return Collections.emptyList();
    }

    Usuario requestingUser = requestingUserOpt.get();

    List<Usuario> users = usuarioRepository.findByNombreUsuarioContaining(nombreUsuario);

    Set<Usuario> alreadyFollowing = requestingUser.getSeguidos();

    for (Usuario user : users) {
      user.setSeguido(alreadyFollowing.contains(user));
    }

    users.removeIf(usuario -> usuario.getId().equals(idUsuario));

    return users;
  }
}
