package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    Pedido findPedidoById(UUID id);
}
