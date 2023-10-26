package javeriana.pontireparte.project.repositories;

import javeriana.pontireparte.project.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UsuarioRepository
        extends JpaRepository<Usuario, Integer> {

    // Nidhood : Modifique el findByUsuarioId por  findById.
    Usuario findById(UUID id);

    // Nidhood : Modifique el findByNombreUsuario por findByNombreusuario.
    Usuario findByNombreusuario(String nombreusuario);

    Usuario findByContrasena(String contrasena);

    // Nidhood : Modifique el deleteUsuarioByUsuarioId por  deleteUsuarioById.
    // Nidhood : Modifique el tipo de dato usuarioId de int por UUID id.
    Usuario deleteUsuarioById(UUID id);
    Usuario findByTipousuario(String tipousuario);
}

