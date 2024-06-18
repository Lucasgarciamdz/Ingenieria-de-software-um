package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.Mensaje;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MensajeDao {

  SessionFactory sessionFactory;

  public MensajeDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Mensaje findByIdWithEtiquetas(Long id) {
    Session session = sessionFactory.getCurrentSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Mensaje> cq = cb.createQuery(Mensaje.class);
    Root<Mensaje> root = cq.from(Mensaje.class);
    root.fetch("etiquetas");
    cq.select(root).where(cb.equal(root.get("id"), id));
    Mensaje mensaje = session.createQuery(cq).uniqueResult();
    Hibernate.initialize(mensaje.getEtiquetas());
    return mensaje;
  }

  public List<Mensaje> findAllWithEtiquetas() {
    Session session = sessionFactory.getCurrentSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Mensaje> cq = cb.createQuery(Mensaje.class);
    Root<Mensaje> root = cq.from(Mensaje.class);
    root.fetch("etiquetas");
    cq.select(root);
    List<Mensaje> mensajes = session.createQuery(cq).getResultList();
    mensajes.forEach(m -> Hibernate.initialize(m.getEtiquetas()));
    return mensajes;
  }
}
