package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.dtos.EtiquetaDto;
import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.repositories.EtiquetaRepository;
import ar.um.edu.microblogging.repositories.MensajeRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EtiquetaService extends DtoMapper implements BaseService<Etiqueta, EtiquetaDto> {

  private final EtiquetaRepository etiquetaRepository;
  private final MensajeRepository mensajeRepository;

  public EtiquetaService(EtiquetaRepository etiquetaRepository, MensajeRepository mensajeRepository) {
    this.etiquetaRepository = etiquetaRepository;
    this.mensajeRepository = mensajeRepository;
  }

  @Override
  public List<Etiqueta> getAll() {
    return etiquetaRepository.findAll();
  }

  @Override
  public Etiqueta getById(Long id) {
    return etiquetaRepository.findById(id).orElse(null);
  }

  @Override
  public Etiqueta save(EtiquetaDto dto) {
    Etiqueta entity = new Etiqueta();
    copyNonNullProperties(dto, entity);
    return this.etiquetaRepository.save(entity);
  }
  
  public Etiqueta saveEntity(Etiqueta entity) {
    return this.etiquetaRepository.save(entity);
  }

  @Override
  public Etiqueta update(Long id, EtiquetaDto dto) {
    Etiqueta entity = this.etiquetaRepository.findById(id).orElse(null);
    assert entity != null;
    copyNonNullProperties(dto, entity);
    return this.etiquetaRepository.save(entity);
  }

  @Override
  public boolean delete(Long etiquetaId) {
    Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId)
        .orElseThrow(() -> new EntityNotFoundException("Etiqueta not found with id " + etiquetaId));

    for (Mensaje mensaje : etiqueta.getMensajes()) {
      mensaje.getEtiquetas().remove(etiqueta);
      mensajeRepository.save(mensaje);
    }

    etiquetaRepository.delete(etiqueta);
    return true;
  }

  public Etiqueta getEtiquetaByNombre(String nombre) {
    Optional<Etiqueta> etiqueta = etiquetaRepository.findByNombre(nombre);
    return etiqueta.orElse(null);
  }
}
