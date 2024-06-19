package ar.um.edu.microblogging.dto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
@Entity
public class Etiqueta extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String nombre;

  @Column(nullable = false)
  private Boolean delMomento;

  @ManyToMany(mappedBy = "etiquetas", fetch = FetchType.LAZY)
  @JsonBackReference
  private Set<Mensaje> mensajes = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Etiqueta etiqueta)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(nombre, etiqueta.nombre) &&
        Objects.equals(delMomento, etiqueta.delMomento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), nombre, delMomento);
  }
}