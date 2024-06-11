package ar.um.edu.microblogging.dto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Etiqueta extends BaseEntity {

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false)
  private boolean delMomento;

  @ManyToMany(mappedBy = "etiquetas")
  private Set<Mensaje> mensajes;
}