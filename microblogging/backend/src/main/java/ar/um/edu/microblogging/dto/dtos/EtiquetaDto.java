package ar.um.edu.microblogging.dto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EtiquetaDto {

  private String nombre;
  private Boolean delMomento;
  private Long idMensaje;
}
