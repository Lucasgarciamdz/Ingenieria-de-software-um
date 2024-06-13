package ar.um.edu.microblogging.dto.dtos;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Data
@Getter
@Setter
public class MensajeDTO {
    private Long id;
    private String texto;
    private Date fechaPublicacion;
    private Long autorId;
    private Long usuarioDestinatarioId;
    private Set<Long> etiquetaIds;
    private Set<Long> usuarioRepublicadoIds;

    // Getters y setters
}
