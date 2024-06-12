package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Mensaje;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MensajeRepository extends BaseRepository<Mensaje, Long> {

    @Query(value="SELECT m FROM Mensaje m WHERE m.usuario.id = :idUsuario OR m.usuario.id IN (SELECT s.seguido_id FROM seguidores_seguidos s WHERE s.seguidor_id = :idUsuario) OR m.id IN (SELECT r.mensaje_id FROM republicaciones r WHERE r.usuario_id = :idUsuario)", nativeQuery = true)
    List<Mensaje> findMyMessagesAndFromFollowedAndReposted(@Param("idUsuario") Long idUsuario);

    @Query(value="INSERT INTO republicaciones (usuario_id, mensaje_id) VALUES (:idUsuario, :idMensaje)", nativeQuery = true)
    void saveMensajeRepublicado(@Param("idUsuario") Long idUsuario, @Param("idMensaje") Long idMensaje);


}
