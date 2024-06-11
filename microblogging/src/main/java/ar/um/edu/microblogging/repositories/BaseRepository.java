package ar.um.edu.microblogging.repositories;

import ar.um.edu.microblogging.dto.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
    
}

