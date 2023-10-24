package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.entities.Foto;
import javeriana.pontireparte.project.entities.Usuario;
import javeriana.pontireparte.project.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        Foto foto = new Foto();

        foto.setId(UUID.randomUUID());
        foto.setTipofoto("Ni idea que va aca");
        foto.setNombre("Foto Por Defecto");
        foto.setDescripcion("No se nisiquiera para que esta este atributo");
        foto.setFoto("https://raw.githubusercontent.com/juanzulu/Desarrollo_fundamentos/main/imagenes/predeterminado.png");
        usuario.setFoto(foto);
        String EstadoSesion = "Activa";
        usuario.setEstadosesion(EstadoSesion);
        String Disponibilidad = "??";
        usuario.setDisponibilidad(Disponibilidad);
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
