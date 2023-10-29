package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {
    Producto findProductoById(UUID id);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombreproducto) LIKE %:palabraclave%")
    List<Producto> findByKeyword(@Param("palabraclave") String palabraclave);
}

