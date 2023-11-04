package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.ProductoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoPedidoRepository extends JpaRepository<ProductoPedido, UUID> {
    ProductoPedido findProductoPedidoById(UUID id);
}
