package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
    Usuario findByEmailAndClave(String email, String clave);
}