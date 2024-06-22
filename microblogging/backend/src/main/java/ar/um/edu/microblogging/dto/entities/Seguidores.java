package ar.um.edu.microblogging.dto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seguidores extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  @JsonBackReference(value = "usuario-seguidores")
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "usuario_seguido_id", nullable = false)
  @JsonBackReference(value = "usuario-seguidos")
  private Usuario usuarioSeguido;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Seguidores seguidores)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(usuario, seguidores.usuario)
        && Objects.equals(usuarioSeguido, seguidores.usuarioSeguido);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), usuario, usuarioSeguido);
  }
}
