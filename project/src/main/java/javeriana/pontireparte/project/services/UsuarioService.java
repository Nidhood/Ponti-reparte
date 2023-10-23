package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.entities.Usuario;
import javeriana.pontireparte.project.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    //Nuevo
    public void addNewUsuario(Usuario usuario) {
        System.out.println(usuario);
      /*  Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByCorreo(usuario.getCorreo());
       if(usuarioOptional.isPresent()){
            throw new IllegalStateException("Correo Repetido");
        }*/
        usuarioRepository.save(usuario);
    }
    public Usuario loginWithUsuario(Usuario usuario) {

        Usuario storedUserDetails = usuarioRepository.findByNombreusuario(usuario.getNombreusuario());
        Usuario storedUserDetails1 = usuarioRepository.findByContrasena(usuario.getContrasena());
        System.out.println("Usuario: " + storedUserDetails);
        System.out.println("Clave: " + storedUserDetails1);
        if(storedUserDetails == null) throw new RuntimeException("Usuario No existe");
        else
        if(storedUserDetails1 == null) throw new RuntimeException("Clave incorrecta");
        return storedUserDetails1;
    }
    public void updateWithID(Usuario usuario){

        // Nidhood : Modifique usuario.getUsuarioId() por usuario.getId().
        Usuario storedUserDetails = usuarioRepository.findById(usuario.getId());
        if(storedUserDetails == null) throw new RuntimeException("ID invalido");
        usuarioRepository.save(usuario);
    }
    public void deleteWithID(Usuario usuario){

        // Nidhood : Modifique usuario.getUsuarioId() por usuario.getId().
        Usuario storedUserDetails = usuarioRepository.findById(usuario.getId());
        usuarioRepository.delete(usuario);

    }
    public Usuario infoWithUsuario(Usuario usuario) {

        // Nidhood : Modifique usuario.getUsuarioId() por usuario.getId().
        Usuario storedUserDetails = usuarioRepository.findById(usuario.getId());
        return storedUserDetails;
    }
}
