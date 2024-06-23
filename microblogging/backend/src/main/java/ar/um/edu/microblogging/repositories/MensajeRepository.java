package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Mensaje;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends BaseRepository<Mensaje, Long> {


  List<Mensaje> findByAutorId(Long autorId);
  
  List<Mensaje> findByEtiquetasNombre(String etiqueta);
  
}
