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
        return this.etiquetaRepository.findAll();
    }

    @Override
    public Etiqueta getById(Long id) {
        return this.etiquetaRepository.findById(id).orElse(null);
    }

    @Override
    public Etiqueta save(Etiqueta entity) {
        return this.etiquetaRepository.save(entity);
    }

    @Override
    public Etiqueta update(Etiqueta entity) {
        return this.etiquetaRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        return this.etiquetaRepository.findById(id).map(etiqueta -> {
            this.etiquetaRepository.delete(etiqueta);
            return true;
        }).orElse(false);
    }

    public List<Etiqueta> getEtiquetasTrending() {
        // Implement your logic to get trending tags here
        return null;
    }
}