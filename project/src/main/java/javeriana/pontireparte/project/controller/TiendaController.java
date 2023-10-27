
package javeriana.pontireparte.project.controller;

import javeriana.pontireparte.project.entities.Producto;
import javeriana.pontireparte.project.entities.Tienda;
import javeriana.pontireparte.project.services.ProductoService;
import javeriana.pontireparte.project.services.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Allow all origins
@RequestMapping(path = "/tiendas")
public class TiendaController {
    private final TiendaService tiendaService;

    @Autowired
    public TiendaController(TiendaService tiendaService) {

        this.tiendaService = tiendaService;
    }

    @GetMapping
    public List<Tienda> getTienda(){

        return tiendaService.getTienda();
    }
    @CrossOrigin(origins = "*") // Allow all origins

    @RequestMapping(value = "/limit/8", method = RequestMethod.GET)
    public List<Tienda> registerNewUsuario(){
        return tiendaService.getTienda();
    }
}
