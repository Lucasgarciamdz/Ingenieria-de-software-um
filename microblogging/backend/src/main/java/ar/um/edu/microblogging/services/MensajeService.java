package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.dtos.MensajeDto;
import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.dto.requests.RepostMensajeDto;
import ar.um.edu.microblogging.repositories.MensajeRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MensajeService extends DtoMapper implements BaseService<Mensaje, MensajeDto> {

  private final MensajeRepository mensajeRepository;
  private final UsuarioService usuarioService;
  private final EtiquetaService etiquetaService;

  public MensajeService(
      MensajeRepository mensajeRepository,
      UsuarioService usuarioService,
      EtiquetaService etiquetaService) {
    this.mensajeRepository = mensajeRepository;
    this.usuarioService = usuarioService;
    this.etiquetaService = etiquetaService;
  }

  @Override
  public List<Mensaje> getAll() {

    List<Mensaje> mensajes = mensajeRepository.findAll();
    return mensajes.stream()
        .filter(mensaje -> mensaje.getUsuarioDestinatario() == null)
        .collect(Collectors.toList());
  }

  @Override
  public Mensaje getById(Long id) {
    Optional<Mensaje> mensaje = mensajeRepository.findById(id);

    if (mensaje.isPresent()) {
      if (mensaje.get().getUsuarioDestinatario() == null) {
        return mensaje.get();
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  @Override
  public boolean delete(Long id) {
    return mensajeRepository
        .findById(id)
        .map(
            mensaje -> {
              mensajeRepository.delete(mensaje);
              return true;
            })
        .orElse(false);
  }

  @Override
  public Mensaje save(MensajeDto request) {
    Usuario autor = usuarioService.getById(request.getAutorId());

    Usuario destinatario =
        request.getDestinatarioId() != null
            ? usuarioService.getById(request.getDestinatarioId())
            : null;

    Set<Etiqueta> etiquetas = new HashSet<>();
    for (String hashtag : request.getEtiquetas()) {
      Etiqueta etiqueta = etiquetaService.getEtiquetaByNombre(hashtag);
      if (etiqueta == null) {
        Etiqueta newEtiqueta = new Etiqueta();
        newEtiqueta.setNombre(hashtag);
        newEtiqueta.setDelMomento(false);
        etiqueta = etiquetaService.saveEntity(newEtiqueta);
      }
      etiquetas.add(etiqueta);
    }

    Set<Usuario> menciones = new HashSet<>();
    Set<Long> mentionedUsernames = request.getMenciones();
    for (Long username : mentionedUsernames) {
      Usuario mentionedUser = usuarioService.getById(username);
      if (mentionedUser != null) {
        menciones.add(mentionedUser);
      }
    }

    Mensaje mensaje = new Mensaje();
    mensaje.setAutor(autor);
    mensaje.setTexto(request.getTexto());
    mensaje.setFechaPublicacion(new Date());
    mensaje.setUsuarioDestinatario(destinatario);
    mensaje.setEtiquetas(etiquetas);
    mensaje.setMenciones(menciones);

    return mensajeRepository.save(mensaje);
  }

  public Mensaje saveEntity(Mensaje entity) {
    return mensajeRepository.save(entity);
  }

  @Override
  public Mensaje update(Long id, MensajeDto request) {
    Mensaje mensaje = mensajeRepository.findById(id).orElse(null);
    assert mensaje != null;
    copyNonNullProperties(request, mensaje);
    return mensajeRepository.save(mensaje);
  }

  //  public List<Mensaje> getMensajesByUsuario(Long id) {
  //    return mensajeRepository.findByAutorId(id);
  //  }
  public List<Mensaje> getMensajesByUsuario(Long id) {
    return mensajeRepository.findByAutorId(id);
  }

  public Mensaje repost(RepostMensajeDto repostMensaje) {
    Usuario usuario = usuarioService.getById(repostMensaje.idUsuario());
    Mensaje mensaje = this.mensajeRepository.findById(repostMensaje.idMensaje()).orElse(null);

    if (usuario != null && mensaje != null) {

      mensaje.getReposts().add(usuario);
      return this.mensajeRepository.save(mensaje);
    } else {
      throw new RuntimeException("Usuario or Mensaje not found");
    }
  }

  public List<Mensaje> getMensajesByEtiqueta(String nombre) {
    return mensajeRepository.findByEtiquetasNombre(nombre);
  }

  public List<Mensaje> getMensajesDeFollowers(Long id) {
    Usuario user = usuarioService.getById(id);
    Set<Usuario> following = user.getSeguidos();
    List<Mensaje> mensajes = new ArrayList<>();
    for (Usuario usuario : following) {
      Set<Mensaje> mensajesUsuario = usuario.getMensajes();
      mensajesUsuario.removeIf(mensaje -> mensaje.getUsuarioDestinatario() != null);
      mensajes.addAll(mensajesUsuario);
    }
    return mensajes;
  }

  public List<Mensaje> getMensajesPrivados(Long id) {
    Usuario user = usuarioService.getById(id);
    List<Mensaje> mensajesEnviados =
        mensajeRepository.findByAutorAndUsuarioDestinatarioIsNotNull(user);
    List<Mensaje> mensajesRecibidos = mensajeRepository.findByUsuarioDestinatario(user);
    List<Mensaje> todosMensajesPrivados = new ArrayList<>();
    todosMensajesPrivados.addAll(mensajesEnviados);
    todosMensajesPrivados.addAll(mensajesRecibidos);
    return todosMensajesPrivados;
  }
}
