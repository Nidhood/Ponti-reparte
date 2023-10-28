package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.TiendaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TiendaProductoRepository extends JpaRepository<TiendaProducto, UUID> {
    @Query("SELECT tp.producto.nombreproducto, tp.cantidaddisponible, tp.producto.foto FROM TiendaProducto tp WHERE tp.tienda.id = :tiendaId")
    List<Object[]> findProductosByTiendaId(@Param("tiendaId") UUID tiendaId);
}
