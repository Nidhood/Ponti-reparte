package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.dto.PedidoRequestDTO;
import javeriana.pontireparte.project.dto.ProductoRequestDTO;
import javeriana.pontireparte.project.entities.*;
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
    private final ProductoService productoService;

    @Autowired
    public TiendaService(TiendaRepository tiendaRepository, TiendaProductoRepository tiendaProductoRepository, ProductoService productoService) {
        this.tiendaRepository = tiendaRepository;
        this.tiendaProductoRepository = tiendaProductoRepository;
        this.productoService = productoService;
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
                .map(tienda -> {
                    TiendaProducto tiendaProducto = new TiendaProducto();
                    Producto producto = new Producto();
                    TiendaProductoId tiendaProductoId = new TiendaProductoId();
                    producto.setId((UUID) tienda[3]);
                    producto.setNombreproducto((String) tienda[0]);
                    producto.setFoto((Foto) tienda[2]);
                    tiendaProductoId.setProducto(producto);
                    tiendaProducto.setId(tiendaProductoId);
                    tiendaProducto.setCantidaddisponible((int) tienda[1]);
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

    public void actualizarInventario(PedidoRequestDTO pedidoRequestDTO) {
        Tienda tienda = tiendaRepository.findTiendaById(pedidoRequestDTO.getTiendaid());
        List<ProductoRequestDTO> productosPedido = pedidoRequestDTO.getProductos();
        for (ProductoRequestDTO productoPedido : productosPedido) {
            TiendaProducto tiendaProducto = tiendaProductoRepository.findTiendaProductoByTiendaAndProductoId(tienda.getId(), productoPedido.getId());
            TiendaProductoId tiendaProductoId = new TiendaProductoId();
            tiendaProductoId.setProducto(productoService.infoWithProducto(productoPedido.getId()));
            tiendaProductoId.setTienda(infoWithTienda(tienda.getId()));
            tiendaProducto.setId(tiendaProductoId);
            int nuevaCantidadDisponible = tiendaProducto.getCantidaddisponible() - productoPedido.getCantidad();
            tiendaProducto.setCantidaddisponible(nuevaCantidadDisponible);
            tiendaProductoRepository.save(tiendaProducto);
        }
    }
}
