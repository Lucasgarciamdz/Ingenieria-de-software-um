package ar.um.edu.microblogging.dto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Etiqueta extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String nombre;

  @Column(nullable = false)
  private Boolean delMomento;

  @ManyToMany(mappedBy = "etiquetas", fetch = FetchType.EAGER)
  @JsonBackReference
  private Set<Mensaje> mensajes = new HashSet<>();
}
