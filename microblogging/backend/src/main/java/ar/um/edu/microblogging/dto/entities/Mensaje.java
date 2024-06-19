package ar.um.edu.microblogging.dto.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Mensaje extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "autor_id", nullable = false)
  @JsonManagedReference
  private Usuario autor;

  @Column(length = 140, nullable = false)
  private String texto;

  @Column(nullable = false)
  private Date fechaPublicacion;

  @ManyToOne
  @JoinColumn(name = "destinatario_id")
  @JsonManagedReference
  private Usuario usuarioDestinatario;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "mensaje_etiqueta",
      joinColumns = @JoinColumn(name = "mensaje_id"),
      inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
  @JsonManagedReference
  private Set<Etiqueta> etiquetas = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Mensaje mensaje)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(autor, mensaje.autor)
        && Objects.equals(texto, mensaje.texto)
        && Objects.equals(fechaPublicacion, mensaje.fechaPublicacion)
        && Objects.equals(usuarioDestinatario, mensaje.usuarioDestinatario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), autor, texto, fechaPublicacion, usuarioDestinatario);
  }
}
