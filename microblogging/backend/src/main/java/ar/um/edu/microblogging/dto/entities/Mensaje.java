package ar.um.edu.microblogging.dto.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Mensaje extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "autor_id", nullable = false)
  private Usuario autor;

  @Column(length = 140, nullable = false)
  private String texto;

  @Column(nullable = false)
  private Date fechaPublicacion;

  @ManyToOne
  @JoinColumn(name = "destinatario_id")
  private Usuario usuarioDestinatario;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "mensaje_etiqueta",
      joinColumns = @JoinColumn(name = "mensaje_id"),
      inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
  @JsonManagedReference
  private Set<Etiqueta> etiquetas = new HashSet<>();
}
