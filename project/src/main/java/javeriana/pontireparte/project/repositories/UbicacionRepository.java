package javeriana.pontireparte.project.repositories;
import javeriana.pontireparte.project.entities.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UbicacionRepository  extends JpaRepository<Ubicacion, UUID> {
    List<Ubicacion> findAll();
    Ubicacion findUbicacionById(UUID id);
}
