package ar.um.edu.microblogging.dto.entities;

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

  @ManyToOne()
  @JoinColumn(name = "autor_id", nullable = false)
  private Usuario autor;

  @Column(length = 140)
  private String texto;

  @Column(nullable = false)
  private Date fechaPublicacion;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "usuario_destinatario_id", nullable = true)
  private Usuario usuarioDestinatario;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinTable(
      name = "mensaje_etiqueta",
      joinColumns = @JoinColumn(name = "mensaje_id"),
      inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
  private Set<Etiqueta> etiquetas = new HashSet<>();


  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "republicaciones",
          joinColumns = @JoinColumn(name = "mensaje_id"),
          inverseJoinColumns = @JoinColumn(name = "usuario_id"))
  private Set<Usuario> usuariosRepublicados = new HashSet<>();
}