package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
  Usuario findByEmailAndClave(String email, String clave);

  @Query(
      value = "SELECT s FROM seguidores_seguidos s WHERE s.seguido_id = :idUsuario",
      nativeQuery = true)
  List<Usuario> findAllFollowers(@Param("idUsuario") Long idUsuario);

  @Query(
      value = "SELECT s FROM seguidores_seguidos s WHERE s.seguidor_id = :idUsuario",
      nativeQuery = true)
  List<Usuario> findAllFollowed(@Param("idUsuario") Long idUsuario);

    Usuario findByEmail(String email);
}
