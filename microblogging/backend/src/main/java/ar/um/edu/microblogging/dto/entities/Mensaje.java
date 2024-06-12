package ar.um.edu.microblogging.dto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Date;
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

  @Column(length = 140)
  private String texto;

  @Column(nullable = false)
  private Date fechaPublicacion;
  
  @ManyToOne
  @JoinColumn(name = "usuario_destinatario_id", nullable = true)
  private Usuario usuarioDestinatario;

  @ManyToMany
  @JoinTable(
      name = "mensaje_etiqueta",
      joinColumns = @JoinColumn(name = "mensaje_id"),
      inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
  private Set<Etiqueta> etiquetas;


  @ManyToMany
  @JoinTable(
          name = "republicaciones",
          joinColumns = @JoinColumn(name = "mensaje_id"),
          inverseJoinColumns = @JoinColumn(name = "usuario_id"))
  private Set<Usuario> usuariosRepublicados;
}