package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, UUID> {
    Tienda findTiendaById(UUID id);
    @Query("SELECT t FROM Tienda t WHERE LOWER(t.nombretienda) LIKE %:palabraclave%")
    List<Tienda> findByKeyword(@Param("palabraclave") String palabraclave);
}

