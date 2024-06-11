package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtiquetaRepository extends BaseRepository<Etiqueta, Long> {

    @Query(value = "SELECT e, COUNT(me.etiqueta_id) AS etiqueta_count " +
            "FROM mensaje_etiqueta me " +
            "JOIN mensajes m ON me.mensaje_id = m.id " +
            "JOIN etiquetas e ON me.etiqueta_id = e.id " +
            "WHERE m.fecha_publicacion >= DATE_SUB(CURDATE(), INTERVAL 1 WEEK) " +
            "GROUP BY e.id " +
            "ORDER BY etiqueta_count DESC " +
            "LIMIT 5",
            nativeQuery = true)
    List<Object[]> findTop5Etiquetas();
}