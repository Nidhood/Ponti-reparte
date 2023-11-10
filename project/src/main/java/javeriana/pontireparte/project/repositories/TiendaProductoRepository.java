package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.TiendaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface TiendaProductoRepository extends JpaRepository<TiendaProducto, UUID> {
    @Query("SELECT tp.id.producto.nombreproducto, tp.cantidaddisponible, tp.id.producto.foto, tp.id.producto.id FROM TiendaProducto tp WHERE tp.id.tienda.id = :tiendaId")
    List<Object[]> findProductosByTiendaId(@Param("tiendaId") UUID tiendaId);

    @Query("SELECT tp.id.tienda.id, tp.id.tienda.nombretienda, tp.id.tienda.foto.foto, tp.cantidaddisponible  FROM TiendaProducto tp WHERE tp.id.producto.id = :productoId")
    List<Object[]> findTiendasByProductoId(@Param("productoId") UUID productoId);

    @Query("SELECT tp FROM TiendaProducto tp WHERE tp.id.tienda.id = :tiendaId AND tp.id.producto.id = :productoId")
    TiendaProducto findTiendaProductoByTiendaAndProductoId(@Param("tiendaId") UUID tiendaId, @Param("productoId") UUID productoId);
}
