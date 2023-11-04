package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.InformacionPago;
import javeriana.pontireparte.project.entities.PedidoInformacionPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoInformacionPagoRepository extends JpaRepository<PedidoInformacionPago, UUID> {
    PedidoInformacionPago findPedidoInformacionPagoById(UUID id);
}
