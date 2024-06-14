package ar.um.edu.microblogging.dto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
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
  private Boolean delMomento;

  @ManyToMany(mappedBy = "etiquetas", fetch = FetchType.LAZY)
  private Set<Mensaje> mensajes = new HashSet<>();
}