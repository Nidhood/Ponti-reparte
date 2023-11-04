
package javeriana.pontireparte.project.controllers;

import javeriana.pontireparte.project.entities.Usuario;
import javeriana.pontireparte.project.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*") // Allow all origins
@RequestMapping(path = "/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getUsuarios(){
        return usuarioService.getUsuarios();
    }

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public void registerNewUsuario(@RequestBody Usuario usuario){
        System.out.println(usuario);
        usuarioService.addNewUsuario(usuario);
    }
    @RequestMapping(value = "/ingreso", method = RequestMethod.POST)
    public Usuario loginUsuario(@RequestBody Usuario usuario){
        // System.out.println("Datos con los que se pretende iniciar sesion: " + usuario);
        Usuario codi = usuarioService.loginWithUsuario(usuario);
        return codi;
    }
    @RequestMapping(value = "/{usuarioId}/infousuario", method = RequestMethod.GET)
    public Usuario infoUsuario(@PathVariable (value = "usuarioId") UUID id){
        //Nuevo
        // System.out.println("Datos: " + id);
        Usuario codi =usuarioService.infoWithUsuario(id);
        return codi;
    }

    @RequestMapping(value = "/{usuarioId}", method = RequestMethod.PUT)
    public void updateUsuario(@PathVariable UUID usuarioId, @RequestBody Usuario usuario){
        // System.out.println("Datos que se van a actualizar: " + usuario);
        usuario.setId(usuarioId);
        usuarioService.updateWithID(usuario);
    }

    @RequestMapping(value = "/{usuarioId}/delete", method = RequestMethod.DELETE)
    public void deleteUsuario(@PathVariable (value = "usuarioId") UUID id){
        // System.out.println("ID del usuario que se va a borrar: " + id);
        usuarioService.deleteWithID(id);
    }

}
