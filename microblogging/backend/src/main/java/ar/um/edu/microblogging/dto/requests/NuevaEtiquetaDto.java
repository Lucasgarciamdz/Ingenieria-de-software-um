package ar.um.edu.microblogging.dto.requests;

import lombok.Data;

@Data
public class NuevaEtiquetaDto {

  private String nombre;
  private Long delMomento;
  private Long idMensaje;
}
