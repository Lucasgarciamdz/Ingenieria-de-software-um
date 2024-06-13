package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.dtos.EtiquetaDTO;
import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.repositories.EtiquetaRepository;
import ar.um.edu.microblogging.repositories.MensajeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;
    private final MensajeRepository mensajeRepository;

    public EtiquetaService(EtiquetaRepository etiquetaRepository, MensajeRepository mensajeRepository) {
        this.etiquetaRepository = etiquetaRepository;
        this.mensajeRepository = mensajeRepository;
    }

    public EtiquetaDTO convertToDTO(Etiqueta etiqueta) {
        EtiquetaDTO dto = new EtiquetaDTO();
        dto.setId(etiqueta.getId());
        dto.setNombre(etiqueta.getNombre());
        dto.setDelMomento(etiqueta.getDelMomento());
        if (etiqueta.getMensajes() != null) {
            dto.setMensajeIds(etiqueta.getMensajes().stream()
                    .map(mensaje -> mensaje.getId())
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public Etiqueta convertToEntity(EtiquetaDTO dto) {
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setId(dto.getId());
        etiqueta.setNombre(dto.getNombre());
        etiqueta.setDelMomento(dto.getDelMomento());
        if (dto.getMensajeIds() != null) {
            etiqueta.setMensajes(dto.getMensajeIds().stream()
                    .map(mensajeId -> mensajeRepository.findById(mensajeId).orElse(null))
                    .collect(Collectors.toSet()));
        } else {
            etiqueta.setMensajes(new HashSet<>());
        }
        return etiqueta;
    }

    public List<EtiquetaDTO> getAll() {
        return etiquetaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EtiquetaDTO getById(Long id) {
        return etiquetaRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public EtiquetaDTO save(EtiquetaDTO dto) {
        Etiqueta etiqueta = convertToEntity(dto);
        return convertToDTO(etiquetaRepository.save(etiqueta));
    }

    public EtiquetaDTO update(EtiquetaDTO dto) {
        Etiqueta etiqueta = convertToEntity(dto);
        if (etiquetaRepository.existsById(etiqueta.getId())) {
            return convertToDTO(etiquetaRepository.save(etiqueta));
        } else {
            return null; // O lanza una excepción si prefieres manejarlo de otra manera
        }
    }

    public boolean delete(Long id) {
        return etiquetaRepository.findById(id).map(etiqueta -> {
            etiquetaRepository.delete(etiqueta);
            return true;
        }).orElse(false);
    }
}
