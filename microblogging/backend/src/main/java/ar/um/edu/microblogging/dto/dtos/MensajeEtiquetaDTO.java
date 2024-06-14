package ar.um.edu.microblogging.dto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeEtiquetaDTO {
        private Long id;
        private String texto;
        private Date fechaPublicacion;
        private Set<String> etiquetas;

        // Getters and setters
    }
}
