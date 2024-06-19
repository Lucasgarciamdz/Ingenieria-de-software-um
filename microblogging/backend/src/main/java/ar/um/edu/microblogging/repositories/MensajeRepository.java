package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Mensaje;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends BaseRepository<Mensaje, Long> {

  @Query("SELECT m FROM Mensaje m LEFT JOIN FETCH m.etiquetas WHERE m.id = :id")
  Mensaje findWithEtiquetas(@Param("id") Long id);
  
}
