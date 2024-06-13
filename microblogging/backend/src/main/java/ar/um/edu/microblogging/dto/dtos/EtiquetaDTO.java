package ar.um.edu.microblogging.dto.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
public class EtiquetaDTO {
    private Long id;
    private String nombre;
    private Boolean delMomento;
    private Set<Long> mensajeIds;

    public EtiquetaDTO() {
        this.mensajeIds = new HashSet<>();
    }
}
