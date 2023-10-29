package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FotoRepository extends JpaRepository<Foto, UUID> {
    Foto findFotoById(UUID id);
    Foto findFotoByNombre(String nombre);
}
