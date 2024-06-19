package ar.um.edu.microblogging.dto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
  @JsonBackReference
  private Set<Mensaje> mensajes;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private Set<Seguidores> seguidores;

  @OneToMany(mappedBy = "usuarioSeguido", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private Set<Seguidores> seguidos;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Usuario usuario)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(nombreUsuario, usuario.nombreUsuario)
        && Objects.equals(email, usuario.email)
        && Objects.equals(clave, usuario.clave)
        && Objects.equals(nombreCompleto, usuario.nombreCompleto)
        && Objects.equals(descripcion, usuario.descripcion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), nombreUsuario, email, clave, nombreCompleto, descripcion);
  }
}
