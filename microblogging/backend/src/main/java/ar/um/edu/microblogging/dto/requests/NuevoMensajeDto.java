package ar.um.edu.microblogging.dto.requests;

import java.util.Set;
import lombok.Data;

@Data
public class NuevoMensajeDto {

  private Long autorId;
  private String texto;
  private Long destinatarioId;
  private Set<Long> etiquetaIds;
}
