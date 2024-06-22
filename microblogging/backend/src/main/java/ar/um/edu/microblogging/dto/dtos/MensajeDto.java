package ar.um.edu.microblogging.dto.dtos;

import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDto {

  private Long id;
  private Long autorId;
  private String texto;
  private Date fechaPublicacion;
  private Long destinatarioId;
  private Set<String> etiquetas;
}
