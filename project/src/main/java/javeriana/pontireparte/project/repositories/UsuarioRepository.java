package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UsuarioRepository
        extends JpaRepository<Usuario, Integer> {

    Usuario findById(UUID id);
    Usuario findByIdjaveriana(String idjaveriana);
    Usuario findByNombreusuario(String nombreusuario);

    Usuario findByContrasena(String contrasena);

    Usuario deleteUsuarioById(UUID id);
    Usuario findByTipousuario(String tipousuario);
}

