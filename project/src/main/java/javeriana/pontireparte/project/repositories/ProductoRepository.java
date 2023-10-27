package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoRepository
        extends JpaRepository<Producto, Integer> {
    Producto findById(UUID id);
}

