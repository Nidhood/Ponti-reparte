package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, UUID> {
    Tienda findTiendaById(UUID id);
}

