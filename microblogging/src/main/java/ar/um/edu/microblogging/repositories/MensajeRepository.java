package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Mensaje;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends BaseRepository<Mensaje, Long> {}
