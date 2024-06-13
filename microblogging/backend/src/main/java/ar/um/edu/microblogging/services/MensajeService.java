package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.dtos.EtiquetaDTO;
import ar.um.edu.microblogging.dto.dtos.MensajeDTO;
import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.repositories.MensajeRepository;
import ar.um.edu.microblogging.repositories.UsuarioRepository;
import ar.um.edu.microblogging.repositories.EtiquetaRepository;
import ar.um.edu.microblogging.services.EtiquetaService;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final EtiquetaRepository etiquetaRepository;

    public MensajeService(MensajeRepository mensajeRepository, UsuarioRepository usuarioRepository, EtiquetaRepository etiquetaRepository) {
        this.mensajeRepository = mensajeRepository;
        this.usuarioRepository = usuarioRepository;
        this.etiquetaRepository = etiquetaRepository;
    }

    public MensajeDTO convertToDTO(Mensaje mensaje) {
        MensajeDTO dto = new MensajeDTO();
        dto.setId(mensaje.getId());
        dto.setTexto(mensaje.getTexto());
        dto.setFechaPublicacion(mensaje.getFechaPublicacion());
        dto.setAutorId(mensaje.getAutor().getId());

        if (mensaje.getUsuarioDestinatario() != null) {
            dto.setUsuarioDestinatarioId(mensaje.getUsuarioDestinatario().getId());
        }

       if (mensaje.getUsuariosRepublicados() != null) {
        dto.setUsuarioRepublicadoIds(mensaje.getUsuariosRepublicados().stream()
                .map(usuario -> usuario.getId())
                .collect(Collectors.toSet()));
        }

       if (dto.getEtiquetaIds() != null) {
        mensaje.setEtiquetas(
                dto.getEtiquetaIds().stream()
                        .map(id -> etiquetaRepository.findById(id).orElse(null))
                        .collect(Collectors.toSet()));
        }

        return dto;
    }

   public Mensaje convertToEntity(MensajeDTO dto) {
        Mensaje mensaje = new Mensaje();
        mensaje.setId(dto.getId());
        mensaje.setTexto(dto.getTexto());
        mensaje.setFechaPublicacion(dto.getFechaPublicacion());
        mensaje.setAutor(usuarioRepository.findById(dto.getAutorId()).orElse(null));
        if (dto.getUsuarioDestinatarioId() != null) {
            mensaje.setUsuarioDestinatario(usuarioRepository.findById(dto.getUsuarioDestinatarioId()).orElse(null));
        }
        if (dto.getEtiquetaIds() != null) {
            mensaje.setEtiquetas(dto.getEtiquetaIds().stream()
                    .map(etiquetaId -> etiquetaRepository.findById(etiquetaId).orElse(null))
                    .collect(Collectors.toSet()));
        } else {
            mensaje.setEtiquetas(new HashSet<>());
        }
        if (dto.getUsuarioRepublicadoIds() != null) {
            mensaje.setUsuariosRepublicados(dto.getUsuarioRepublicadoIds().stream()
                    .map(usuarioId -> usuarioRepository.findById(usuarioId).orElse(null))
                    .collect(Collectors.toSet()));
        } else {
            mensaje.setUsuariosRepublicados(new HashSet<>());
        }
        return mensaje;
}

    public List<MensajeDTO> getAll() {
        return mensajeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MensajeDTO getById(Long id) {
        return mensajeRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public MensajeDTO save(MensajeDTO dto) {
        Mensaje mensaje = convertToEntity(dto);
        return convertToDTO(mensajeRepository.save(mensaje));
    }

    public MensajeDTO update(MensajeDTO dto) {

        Mensaje mensajeExistente = mensajeRepository.findById(dto.getId()).orElse(null);

        if (dto.getTexto() == null) {
            dto.setTexto(mensajeExistente.getTexto());
        }
        if (dto.getAutorId() == null) {
            dto.setAutorId(mensajeExistente.getAutor().getId());
        }
        if (dto.getFechaPublicacion() == null) {
            dto.setFechaPublicacion(mensajeExistente.getFechaPublicacion());
        }
        if (dto.getUsuarioDestinatarioId() == null) {
            if (mensajeExistente.getUsuarioDestinatario() != null) {
                dto.setUsuarioDestinatarioId(mensajeExistente.getUsuarioDestinatario().getId());
            } else {
                dto.setUsuarioDestinatarioId(null);
            }
        }
        if (dto.getEtiquetaIds() == null) {
            dto.setEtiquetaIds(mensajeExistente.getEtiquetas().stream()
                    .map(etiqueta -> etiqueta.getId())
                    .collect(Collectors.toSet()));
        }
        if (dto.getUsuarioRepublicadoIds() == null) {
            dto.setUsuarioRepublicadoIds(mensajeExistente.getUsuariosRepublicados().stream()
                    .map(usuario -> usuario.getId())
                    .collect(Collectors.toSet()));
        }

        Mensaje mensaje = convertToEntity(dto);
        if (mensajeRepository.existsById(mensaje.getId())) {
            return convertToDTO(mensajeRepository.save(mensaje));
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        return mensajeRepository.findById(id).map(mensaje -> {
            mensajeRepository.delete(mensaje);
            return true;
        }).orElse(false);
    }
}
