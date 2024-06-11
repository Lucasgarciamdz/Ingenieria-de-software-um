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
        return List.of();
    }

    @Override
    public Mensaje getById(Long id) {
        return null;
    }

    @Override
    public Mensaje save(Mensaje entity) {
        return null;
    }

    @Override
    public Mensaje update(Mensaje entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
