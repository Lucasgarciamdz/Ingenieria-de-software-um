package ar.um.edu.microblogging.dto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

  private String nombreUsuario;
  private String email;
  private byte[] foto;
  private String clave;
  private String nombreCompleto;
  private String descripcion;
}
