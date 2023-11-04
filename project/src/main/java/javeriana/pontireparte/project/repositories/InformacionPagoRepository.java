package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.InformacionPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InformacionPagoRepository extends JpaRepository<InformacionPago, UUID> {
    InformacionPago findInformacionPagoById(UUID id);
}
