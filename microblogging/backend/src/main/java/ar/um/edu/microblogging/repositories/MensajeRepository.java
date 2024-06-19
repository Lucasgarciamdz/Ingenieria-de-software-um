package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Mensaje;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends BaseRepository<Mensaje, Long> {

  @Query("SELECT m FROM Mensaje m LEFT JOIN FETCH m.etiquetas WHERE m.id = :id")
  Mensaje findWithEtiquetas(@Param("id") Long id);
}
