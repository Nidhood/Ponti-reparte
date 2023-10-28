
package javeriana.pontireparte.project.controllers;

import javeriana.pontireparte.project.entities.Tienda;
import javeriana.pontireparte.project.services.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @RequestMapping(value = "/limit/8", method = RequestMethod.GET)
    public List<Tienda> registerNewUsuario(){
        return tiendaService.getTodasLasTiendasConProductos();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Tienda getInfoTienda(@PathVariable (value = "id") UUID id){
        Tienda tienda = tiendaService.infoWithTienda(id);
        return tienda;
    }
}
