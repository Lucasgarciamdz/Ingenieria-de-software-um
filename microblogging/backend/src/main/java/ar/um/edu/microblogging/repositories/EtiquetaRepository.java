package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetaRepository extends BaseRepository<Etiqueta, Long> {

  @Query(
      value =
          "SELECT e, COUNT(me.etiqueta_id) AS etiqueta_count "
              + "FROM mensaje_etiqueta me "
              + "JOIN mensajes m ON me.mensaje_id = m.id "
              + "JOIN etiquetas e ON me.etiqueta_id = e.id "
              + "WHERE m.fecha_publicacion >= CURRENT_DATE - INTERVAL :interval "
              + "GROUP BY e.id "
              + "ORDER BY etiqueta_count DESC "
              + "LIMIT 5",
      nativeQuery = true)
  List<Object[]> findTop5Etiquetas(@Param("interval") String interval);

  Optional<Etiqueta> findByNombre(String nombre);
}
