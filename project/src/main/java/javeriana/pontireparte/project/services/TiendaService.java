package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.entities.Foto;
import javeriana.pontireparte.project.entities.Producto;
import javeriana.pontireparte.project.entities.Tienda;
import javeriana.pontireparte.project.entities.TiendaProducto;
import javeriana.pontireparte.project.repositories.TiendaProductoRepository;
import javeriana.pontireparte.project.repositories.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class TiendaService {
    private final TiendaRepository tiendaRepository;
    private final TiendaProductoRepository tiendaProductoRepository;

    @Autowired
    public TiendaService(TiendaRepository tiendaRepository, TiendaProductoRepository tiendaProductoRepository) {
        this.tiendaRepository = tiendaRepository;
        this.tiendaProductoRepository = tiendaProductoRepository;
    }

    public List<Tienda> getTienda(){
        return tiendaRepository.findAll();
    }

    public List<Tienda> getTodasLasTiendasConProductos() {
        List<Tienda> todasLasTiendas = tiendaRepository.findAll();
        todasLasTiendas.forEach(tienda -> {
            tienda.setProductos(mapToTiendasProducto(tiendaProductoRepository.findProductosByTiendaId(tienda.getId())));
        });
        return todasLasTiendas;
    }

    public Tienda infoWithTienda(UUID productoId) {
        Tienda tienda = tiendaRepository.findTiendaById(productoId);
        tienda.setProductos(mapToTiendasProducto(tiendaProductoRepository.findProductosByTiendaId(productoId)));
        return tienda;
    }

    private List<TiendaProducto> mapToTiendasProducto(List<Object[]> tiendasProductoConCantidad) {
        return tiendasProductoConCantidad.stream()
                .map(obj -> {
                    TiendaProducto tiendaProducto = new TiendaProducto();
                    Producto producto = new Producto();
                    producto.setNombreproducto((String) obj[0]);
                    producto.setFoto((Foto) obj[2]);
                    tiendaProducto.setProducto(producto);
                    tiendaProducto.setCantidaddisponible((int) obj[1]);
                    return tiendaProducto;
                })
                .collect(Collectors.toList());
    }

    public List<Tienda> buscarTiendasPorPalabraClave(String palabraclave) {
        List<Tienda> productosEncontrados = tiendaRepository.findByKeyword(palabraclave);
        productosEncontrados.forEach(tienda -> {
            tienda.setProductos(mapToTiendasProducto(tiendaProductoRepository.findProductosByTiendaId(tienda.getId())));
        });
        return productosEncontrados;
    }
}
