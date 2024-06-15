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

  @OneToMany(mappedBy = "autor")
  private Set<Mensaje> mensajes;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Seguidores> seguidores;
}