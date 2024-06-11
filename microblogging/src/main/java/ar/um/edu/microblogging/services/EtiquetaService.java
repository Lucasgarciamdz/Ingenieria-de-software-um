package ar.um.edu.microblogging.services;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.repositories.EtiquetaRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EtiquetaService implements BaseService<Etiqueta> {

    private final EtiquetaRepository etiquetaRepository;

    public EtiquetaService(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }

    @Override
    public List<Etiqueta> getAll() {
        return List.of();
    }

    @Override
    public Etiqueta getById(Long id) {
        return null;
    }

    @Override
    public Etiqueta save(Etiqueta entity) {
        return null;
    }

    @Override
    public Etiqueta update(Etiqueta entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
