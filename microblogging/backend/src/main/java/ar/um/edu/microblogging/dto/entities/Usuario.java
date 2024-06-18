package ar.um.edu.microblogging.dto.entities;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Usuario extends BaseEntity {

  @Column(length = 15, nullable = false, unique = true)
  private String nombreUsuario;

  @Column(nullable = false, unique = true)
  private String email;

  @Lob private byte[] foto;

  @Column(nullable = false)
  private String clave;

  private String nombreCompleto;

  private String descripcion;

  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Mensaje> mensajes;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Seguidores> seguidores;

  @OneToMany(mappedBy = "usuarioSeguido", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Seguidores> seguidos;
}
