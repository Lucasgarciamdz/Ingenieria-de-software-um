package ar.um.edu.microblogging.dto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Usuario extends BaseEntity {

  @Column(length = 15)
  private String username;

  private String email;

  @Lob
  private byte[] foto;

  private String clave;

  private String nombreCompleto;

  private String descripcion;

  @OneToMany(mappedBy = "autor")
  private Set<Mensaje> mensajes;

  @ManyToMany
  @JoinTable(
      name = "seguidores_seguidos",
      joinColumns = @JoinColumn(name = "seguido_id"),
      inverseJoinColumns = @JoinColumn(name = "seguidor_id"))
  private Set<Usuario> seguidores;

  @ManyToMany(mappedBy = "seguidores")
  private Set<Usuario> seguidos;

  @ManyToMany(mappedBy = "usuariosRepublicados")
  private Set<Mensaje> mensajesRepublicados;
}