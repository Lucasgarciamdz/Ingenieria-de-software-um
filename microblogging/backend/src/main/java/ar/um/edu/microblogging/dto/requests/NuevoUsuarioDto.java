package ar.um.edu.microblogging.dto.requests;

import ar.um.edu.microblogging.dto.entities.Mensaje;
import java.util.Set;
import lombok.Data;

@Data
public class NuevoUsuarioDto {
  private Long usuarioId;
  private String nombre;
  private String email;
  private String clave;
  private byte[] foto;
  private String nombreCompleto;
  private String descripcion;
  private Set<Mensaje> mensajes;
}
