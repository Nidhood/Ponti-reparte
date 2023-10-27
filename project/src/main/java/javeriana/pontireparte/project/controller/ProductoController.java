package javeriana.pontireparte.project.controller;
import javeriana.pontireparte.project.entities.Producto;
import javeriana.pontireparte.project.services.ProductoService;
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


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Producto getInfoProducto(@PathVariable (value = "id") UUID id){
    Producto producto = productoService.infoWithProducto(id);

        return producto;
    }
}
