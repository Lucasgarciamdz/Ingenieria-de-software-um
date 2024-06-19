package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.dto.requests.NuevoMensajeDto;
import ar.um.edu.microblogging.repositories.EtiquetaRepository;

import ar.um.edu.microblogging.repositories.MensajeRepository;
import ar.um.edu.microblogging.repositories.UsuarioRepository;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {

  private final MensajeRepository mensajeRepository;
  private final UsuarioRepository usuarioRepository;
  private final EtiquetaRepository etiquetaRepository;

  public MensajeService(
      MensajeRepository mensajeRepository,
      UsuarioRepository usuarioRepository,
      EtiquetaRepository etiquetaRepository) {
    this.mensajeRepository = mensajeRepository;
    this.usuarioRepository = usuarioRepository;
    this.etiquetaRepository = etiquetaRepository;
  }

  public List<Mensaje> getAll() {
    return mensajeRepository.findAll();
  }

  public Mensaje getById(Long id) {
    Optional<Mensaje> mensaje = mensajeRepository.findById(id);
    return mensaje.orElse(null);
  }


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

  public Mensaje guardarMensaje(NuevoMensajeDto request) {
    Usuario autor =
        usuarioRepository
            .findById(request.getAutorId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    Usuario destinatario =
        request.getDestinatarioId() != null
            ? usuarioRepository.findById(request.getDestinatarioId()).orElse(null)
            : null;

    Set<Etiqueta> etiquetas = new HashSet<>();
    if (request.getEtiquetaIds() != null) {
      etiquetas.addAll(
          request.getEtiquetaIds().stream()
              .map(
                  id ->
                      etiquetaRepository
                          .findById(id)
                          .orElseThrow(() -> new RuntimeException("Etiqueta no encontrada")))
              .collect(Collectors.toSet()));
    }

    Mensaje mensaje = new Mensaje();
    mensaje.setAutor(autor);
    mensaje.setTexto(request.getTexto());
    mensaje.setFechaPublicacion(new Date());
    mensaje.setUsuarioDestinatario(destinatario);
    mensaje.setEtiquetas(etiquetas);

    return mensajeRepository.save(mensaje);
  }

  public Mensaje update(Long idMensaje, NuevoMensajeDto request) {
    Mensaje mensaje =
        mensajeRepository
            .findById(idMensaje)
            .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));

    if (request.getTexto() != null) {
      mensaje.setTexto(request.getTexto());
    }

    if (request.getDestinatarioId() != null) {
      Usuario destinatario =
          usuarioRepository
              .findById(request.getDestinatarioId())
              .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));
      mensaje.setUsuarioDestinatario(destinatario);
    }

    if (request.getEtiquetaIds() != null) {
      Set<Etiqueta> etiquetas =
          request.getEtiquetaIds().stream()
              .map(
                  id ->
                      etiquetaRepository
                          .findById(id)
                          .orElseThrow(() -> new RuntimeException("Etiqueta no encontrada")))
              .collect(Collectors.toSet());
      mensaje.setEtiquetas(etiquetas);
    }

    return mensajeRepository.save(mensaje);
  }

  private Set<String> extractHashtags(String texto) {
    Set<String> hashtags = new HashSet<>();
    String[] words = texto.split("\\s+");
    for (String word : words) {
      if (word.startsWith("#")) {
        hashtags.add(word.substring(1));
      }
    }
    return hashtags;
  }
}
