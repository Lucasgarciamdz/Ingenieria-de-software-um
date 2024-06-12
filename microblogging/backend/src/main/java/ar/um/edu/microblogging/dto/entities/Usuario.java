package ar.um.edu.microblogging.dto.entities;

import jakarta.persistence.*;

import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Usuario extends BaseEntity {

  @Column(length = 15)
  private String nombreUsuario;

  private String email;

  @Lob
  private byte[] foto;

  private String clave;

  private String nombreCompleto;

  private String descripcion;

  @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
  private Set<Mensaje> mensajes;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "seguidores_seguidos",
      joinColumns = @JoinColumn(name = "seguido_id"),
      inverseJoinColumns = @JoinColumn(name = "seguidor_id"))

  private Set<Usuario> seguidores;

  @ManyToMany(mappedBy = "seguidores", fetch = FetchType.LAZY)
  private Set<Usuario> seguidos;

  @ManyToMany(mappedBy = "usuariosRepublicados", fetch = FetchType.LAZY)
  private Set<Mensaje> mensajesRepublicados;
}