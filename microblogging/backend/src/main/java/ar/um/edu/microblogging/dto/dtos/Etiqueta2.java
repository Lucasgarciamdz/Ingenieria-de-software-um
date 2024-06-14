package ar.um.edu.microblogging.dto.dtos;

public class Etiqueta2 {
    private Long id;
    private String nombre;

    // Constructors
    public Etiqueta2(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
