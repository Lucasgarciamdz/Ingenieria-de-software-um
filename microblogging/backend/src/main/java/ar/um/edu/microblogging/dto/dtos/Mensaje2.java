package ar.um.edu.microblogging.dto.dtos;

import java.util.Set;

public class Mensaje2 {
    private Long id;
    private String texto;
    private Date fechaPublicacion;
    private Set<EtiquetaDTO> etiquetas;

    // Constructors
    public Mensaje2(Long id, String texto, Date fechaPublicacion, Set<EtiquetaDTO> etiquetas) {
        this.id = id;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.etiquetas = etiquetas;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Set<EtiquetaDTO> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<EtiquetaDTO> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
