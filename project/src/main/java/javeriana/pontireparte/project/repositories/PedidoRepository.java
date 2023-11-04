package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    Pedido findPedidoById(UUID id);
    boolean existsByNumeropedido(int numeropedido);
}
