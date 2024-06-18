package ar.um.edu.microblogging.dto.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Seguidores extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "usuario_seguido_id", nullable = false)
  private Usuario usuarioSeguido;
}
