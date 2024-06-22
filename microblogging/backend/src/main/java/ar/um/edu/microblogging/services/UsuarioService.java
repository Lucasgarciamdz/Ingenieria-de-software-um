package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.dtos.UsuarioDto;
import ar.um.edu.microblogging.dto.entities.Seguidores;
import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.dto.requests.FollowUserDto;
import ar.um.edu.microblogging.repositories.SeguidoresRepository;
import ar.um.edu.microblogging.repositories.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService extends DtoMapper implements BaseService<Usuario, UsuarioDto> {

  private final UsuarioRepository usuarioRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final SeguidoresRepository seguidoresRepository;

  public UsuarioService(
      UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder, SeguidoresRepository seguidoresRepository) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
    this.seguidoresRepository = seguidoresRepository;
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

      Seguidores seguidor = new Seguidores();
      seguidor.setUsuario(usuario);
      seguidor.setUsuarioSeguido(usuarioFollow);
      usuario.getSeguidos().add(seguidor);

      seguidoresRepository.save(seguidor);

      return usuarioRepository.save(usuario);
      
    } else {
      throw new Exception("No existe perro, fijate logi");
    }
  }
}
