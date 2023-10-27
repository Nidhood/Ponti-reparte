
package javeriana.pontireparte.project.controller;

import javeriana.pontireparte.project.entities.Producto;
import javeriana.pontireparte.project.entities.Usuario;
import javeriana.pontireparte.project.services.ProductoService;
import javeriana.pontireparte.project.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*") // Allow all origins
@RequestMapping(path = "/productos")
public class ProductoController {
    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> getProducto(){

        return productoService.getProducto();
    }
    @CrossOrigin(origins = "*") // Allow all origins

    @RequestMapping(value = "/limit/8", method = RequestMethod.GET)
    public List<Producto> registerNewUsuario(){
        return productoService.getProducto();
    }
}
