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
        System.out.println(usuario);
        usuarioRepository.save(usuario);
    }
    public Usuario loginWithUsuario(Usuario usuario) {
        String TipoPorDefecto = "cliente";
        Usuario storedUserDetails = usuarioRepository.findByNombreusuario(usuario.getNombreusuario());
        if(storedUserDetails == null) throw new RuntimeException("Usuario No existe");
        else
        if(storedUserDetails.getContrasena() == null) throw new RuntimeException("Clave incorrecta");
        else
        /*if(!storedUserDetails.equals(TipoPorDefecto)) throw new RuntimeException("Tipo Usuario distinto a cliente");
        else*/
        System.out.println(usuario);
        System.out.println("Nombre de usuario: " + storedUserDetails.getNombreusuario());
        System.out.println("Contrasena: " + storedUserDetails.getContrasena());
        System.out.println("Tipo Usuario: " + storedUserDetails.getTipousuario());

        return storedUserDetails;
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
    public Usuario infoWithUsuario(UUID usuarioId) {

        // Nidhood : Modifique usuario.getUsuarioId() por usuario.getId().
        Usuario storedUserDetails = usuarioRepository.findById(usuarioId);
        return storedUserDetails;
    }
}
