package javeriana.pontireparte.project.controllers;


import javeriana.pontireparte.project.entities.Producto;
import javeriana.pontireparte.project.entities.Tienda;
import javeriana.pontireparte.project.services.ProductoService;
import javeriana.pontireparte.project.services.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buscar")
@CrossOrigin(origins = "*") // Permitir todas las solicitudes cruzadas
public class BuscarController {
    private final ProductoService productoService;
    private final TiendaService tiendaService;

    @Autowired
    public  BuscarController(ProductoService productoService,TiendaService tiendaService) {
        this.productoService = productoService;
        this.tiendaService = tiendaService;
    }

    @GetMapping("/productos/{palabraclave}")
    public List<Producto> buscarProductosPorPalabraClave(@PathVariable String palabraclave) {
        List<Producto> productosEncontrados = productoService.buscarProductosPorPalabraClave(palabraclave);
        return productosEncontrados;
    }

    @GetMapping("/tiendas/{palabraclave}")
    public List<Tienda> buscarTiendasPorPalabraClave(@PathVariable String palabraclave) {
        List<Tienda> tiendasEncontradas = tiendaService.buscarTiendasPorPalabraClave(palabraclave);
        return tiendasEncontradas;
    }
}
