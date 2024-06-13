package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.repositories.MensajeRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MensajeService implements BaseService<Mensaje> {

    private final MensajeRepository mensajeRepository;

    public MensajeService(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    @Override
    public List<Mensaje> getAll() {
        return this.mensajeRepository.findAll();
    }

    @Override
    public Mensaje getById(Long id) {
        return this.mensajeRepository.findById(id).orElse(null);
    }

    @Override
    public Mensaje save(Mensaje entity) {
        return this.mensajeRepository.save(entity);
    }

    @Override
    public Mensaje update(Mensaje entity) {
     if (mensajeRepository.existsById(entity.getId())) {
       return this.mensajeRepository.save(entity);
     } else {
       return null; // O lanza una excepción si prefieres manejarlo de otra manera
     }}

    @Override
    public boolean delete(Long id) {
        return this.mensajeRepository.findById(id).map(mensaje -> {
            this.mensajeRepository.delete(mensaje);
            return true;
        }).orElse(false);
    }
}