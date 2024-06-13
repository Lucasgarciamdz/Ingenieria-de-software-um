package ar.um.edu.microblogging.dto.dtos;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Data
public class MensajeDTO {
    private Long id;
    private String texto;
    private Date fechaPublicacion;
    private Long autorId;
    private Long usuarioDestinatarioId;
    private Set<Long> etiquetaIds;
    private Set<Long> usuarioRepublicadoIds;

    // Getters y setters

    public Long getUsuarioDestinatarioId() {
        return usuarioDestinatarioId;
    }

    public void setUsuarioDestinatarioId(Long usuarioDestinatarioId) {
        this.usuarioDestinatarioId = usuarioDestinatarioId;
    }

    public Set<Long> getUsuarioRepublicadoIds() {
        return usuarioRepublicadoIds;
    }

    public void setUsuarioRepublicadoIds(Set<Long> usuarioRepublicadoIds) {
        this.usuarioRepublicadoIds = usuarioRepublicadoIds;
    }

    public Set<Long> getEtiquetaIds() {
        return etiquetaIds;
    }

    public void setEtiquetaIds(Set<Long> etiquetaIds) {
        this.etiquetaIds = etiquetaIds;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
