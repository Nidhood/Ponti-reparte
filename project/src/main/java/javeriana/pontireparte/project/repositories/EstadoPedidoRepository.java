package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, UUID> {
    EstadoPedido findEstadoPedidoById(UUID id);
}
