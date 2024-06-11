package ar.um.edu.microblogging.dto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class BaseEntity {

  @Id
  private Long id;
}
