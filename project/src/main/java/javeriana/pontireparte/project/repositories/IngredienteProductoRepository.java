package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.IngredienteProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IngredienteProductoRepository extends JpaRepository<IngredienteProducto, UUID> {
    @Query("SELECT ip.ingrediente.nombreingrediente, ip.cantidad FROM IngredienteProducto ip WHERE ip.producto.id = :productoId")
    List<Object[]> findIngredientesByProductoId(@Param("productoId") UUID productoId);
}