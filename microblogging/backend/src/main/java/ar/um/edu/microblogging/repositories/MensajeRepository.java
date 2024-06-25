package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.dto.entities.Usuario;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends BaseRepository<Mensaje, Long> {

  List<Mensaje> findByAutorId(Long autorId);

  List<Mensaje> findByEtiquetasNombre(String etiqueta);

  List<Mensaje> findByUsuarioDestinatario(Usuario user);

  List<Mensaje> findByAutorAndUsuarioDestinatarioIsNotNull(Usuario user);
}
