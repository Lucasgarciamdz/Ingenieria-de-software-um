package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {


  Usuario findByEmail(String email);
  
  List<Usuario> findByNombreUsuarioContaining(String nombreUsuario);
  
}
