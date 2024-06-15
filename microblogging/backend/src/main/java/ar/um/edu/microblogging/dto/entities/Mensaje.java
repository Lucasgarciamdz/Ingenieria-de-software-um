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


  private Usuario autor;

  @Column(length = 140)
  private String texto;

  @Column(nullable = false)
  private Date fechaPublicacion;
  

  private Usuario usuarioDestinatario;


}