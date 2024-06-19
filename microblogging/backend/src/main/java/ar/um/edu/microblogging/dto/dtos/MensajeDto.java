package ar.um.edu.microblogging.dto.dtos;

import java.util.Date;
import java.util.Set;
import lombok.Data;

@Data
public class MensajeDto {
  
  private Long id;
  private Long autorId;
  private String texto;
  private Date fechaPublicacion;
  private Long usuarioDestinatarioId;
  private Set<Long> etiquetaIds;
}
