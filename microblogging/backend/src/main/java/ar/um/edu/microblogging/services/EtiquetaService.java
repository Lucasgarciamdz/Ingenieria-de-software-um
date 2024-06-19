package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.requests.NuevaEtiquetaDto;
import ar.um.edu.microblogging.repositories.EtiquetaRepository;
import ar.um.edu.microblogging.repositories.MensajeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EtiquetaService {

  private final EtiquetaRepository etiquetaRepository;
  private final MensajeRepository mensajeRepository;

  public EtiquetaService(
      EtiquetaRepository etiquetaRepository, MensajeRepository mensajeRepository) {
    this.etiquetaRepository = etiquetaRepository;
    this.mensajeRepository = mensajeRepository;
  }

  public List<Etiqueta> getAll() {
    return etiquetaRepository.findAll();
  }

  public Etiqueta getById(Long id) {
    return etiquetaRepository.findById(id).orElse(null);
  }

  public Etiqueta save(Etiqueta etiqueta) {
    return etiquetaRepository.save(etiqueta);
  }

  public Etiqueta create(NuevaEtiquetaDto nuevaEtiquetaDto) {
    Etiqueta nuevaEtiqueta = new Etiqueta();
    nuevaEtiqueta.setNombre(nuevaEtiquetaDto.getNombre());
    nuevaEtiqueta.setDelMomento(nuevaEtiquetaDto.getDelMomento() == 1);
    // Lógica adicional si es necesario, como asociar con mensajes.
    return etiquetaRepository.save(nuevaEtiqueta);
  }

  public Etiqueta update(Long id, NuevaEtiquetaDto nuevaEtiquetaDto) {
    Etiqueta etiquetaExistente = etiquetaRepository.findById(id).orElse(null);
    if (etiquetaExistente != null) {
      if (nuevaEtiquetaDto.getNombre() != null) {
        etiquetaExistente.setNombre(nuevaEtiquetaDto.getNombre());
      }
      if (nuevaEtiquetaDto.getDelMomento() != null) {
        etiquetaExistente.setDelMomento(nuevaEtiquetaDto.getDelMomento() == 1);
      }
      return etiquetaRepository.save(etiquetaExistente);
    } else {
      return null;
    }
  }

  public boolean delete(Long id) {
    return etiquetaRepository
        .findById(id)
        .map(
            etiqueta -> {
              etiquetaRepository.delete(etiqueta);
              return true;
            })
        .orElse(false);
  }
}
