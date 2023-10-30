package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.entities.Foto;
import javeriana.pontireparte.project.entities.Usuario;
import javeriana.pontireparte.project.repositories.FotoRepository;
import javeriana.pontireparte.project.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class UsuarioService {

    private final FotoRepository fotoRepository;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(FotoRepository fotoRepository, UsuarioRepository usuarioRepository) {
        this.fotoRepository = fotoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    //Nuevo
    public void addNewUsuario(Usuario usuario) {
        if (usuario.getFoto() == null) {
            UUID fotoPorDefectoId = UUID.fromString("aacde9fd-2feb-44ec-82fa-9c745eb85a9f");
            Foto fotoPorDefecto = fotoRepository.findFotoById(fotoPorDefectoId);
            usuario.setFoto(fotoPorDefecto);
        }
        usuario.setEstadosesion("Conectada");
        usuario.setDisponibilidad("Disponible");
        // System.out.println(usuario);
        usuarioRepository.save(usuario);
    }
    public Usuario loginWithUsuario(Usuario usuario) {
        Usuario storedUserDetails = usuarioRepository.findByNombreusuario(usuario.getNombreusuario());
        if(storedUserDetails == null) throw new RuntimeException("Usuario No existe");
        if(storedUserDetails.getContrasena() == null) throw new RuntimeException("Clave incorrecta");
        if(!storedUserDetails.getTipousuario().equals("Comprador") && !storedUserDetails.getTipousuario().equals("Repartidor") ) throw new RuntimeException("Tipo usuario no disponible!");
        // System.out.println(usuario);
        // System.out.println("Nombre de usuario: " + storedUserDetails.getNombreusuario());
        // System.out.println("Contrasena: " + storedUserDetails.getContrasena());
        // System.out.println("Tipo Usuario: " + storedUserDetails.getTipousuario());
        return storedUserDetails;
    }
    public void updateWithID(Usuario usuario) {
        UUID usuarioId = usuario.getId();
        String nombreFoto = usuario.getFoto().getNombre();
        Usuario existingUsuario = usuarioRepository.findUsuarioById(usuarioId);
        Foto existingFoto = fotoRepository.findFotoByNombre(nombreFoto);

        if (usuario.getNombreusuario() != null && !usuario.getNombreusuario().isEmpty()) {
            existingUsuario.setNombreusuario(usuario.getNombreusuario());
        }

        if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
            existingUsuario.setContrasena(usuario.getContrasena());
        }

        if (usuario.getNombre() != null && !usuario.getNombre().isEmpty()) {
            existingUsuario.setNombre(usuario.getNombre());
        }

        if (usuario.getCorreoinstitucional() != null && !usuario.getCorreoinstitucional().isEmpty()) {
            existingUsuario.setCorreoinstitucional(usuario.getCorreoinstitucional());
        }

        if (usuario.getApellido() != null && !usuario.getApellido().isEmpty()) {
            existingUsuario.setApellido(usuario.getApellido());
        }

        if (usuario.getTelefono() != null && !usuario.getTelefono().isEmpty()) {
            existingUsuario.setTelefono(usuario.getTelefono());
        }

        if (existingFoto != null) {
            existingUsuario.setFoto(existingFoto);
        }
        usuarioRepository.save(existingUsuario);
    }

    public void deleteWithID(UUID usuarioId){
        Usuario storedUserDetails = usuarioRepository.findUsuarioById(usuarioId);
        usuarioRepository.delete(storedUserDetails);
    }
    public Usuario infoWithUsuario(UUID usuarioId) {

        Usuario storedUserDetails = usuarioRepository.findUsuarioById(usuarioId);
        return storedUserDetails;
    }
}
