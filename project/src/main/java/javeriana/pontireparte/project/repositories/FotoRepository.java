package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FotoRepository extends JpaRepository<Foto, UUID> {
    Foto findFotoById(UUID id);
    Foto findFotoByNombre(String nombre);
}
