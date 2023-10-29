package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.dto.PedidoRequestDTO;
import javeriana.pontireparte.project.dto.ProductoDTO;
import javeriana.pontireparte.project.entities.Pedido;
import javeriana.pontireparte.project.entities.Producto;
import javeriana.pontireparte.project.entities.ProductoPedido;
import javeriana.pontireparte.project.entities.Ubicacion;
import javeriana.pontireparte.project.repositories.PedidoRepository;
import javeriana.pontireparte.project.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UbicacionRepository ubicacionRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, UbicacionRepository ubicacionRepository) {
        this.pedidoRepository = pedidoRepository;
        this.ubicacionRepository = ubicacionRepository;
    }

    @Transactional
    public void insertarPedido(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido();

        // Configurar atributos del pedido
        pedido.setCompradorid(pedidoRequestDTO.getCompradorId());
        pedido.setTiendaid(pedidoRequestDTO.getTiendaId());
        pedido.setTipopedido(pedidoRequestDTO.getTipoPedido());
        pedido.setValortotal(pedidoRequestDTO.getValorTotal());
        // Otros atributos...

        // Configurar ubicación
        // Suponiendo que tienes un método para buscar la ubicación por ID
        Ubicacion ubicacion = ubicacionRepository.findUbicacionById(pedidoRequestDTO.getUbicacionId());
        pedido.setUbicacionid(ubicacion.getId());

        // Configurar productos
        List<ProductoDTO> productosDTO = pedidoRequestDTO.getProductos();
        if (productosDTO != null) {
            for (ProductoDTO productoDTO : productosDTO) {
                Producto producto = new Producto();
                producto.setId(producto.getId()); // Suponiendo que tienes un método para buscar el producto por ID
                // Configurar otros atributos del producto si es necesario
                // ...

                ProductoPedido productosPedidos = new ProductoPedido();
                productosPedidos.setProducto(producto);
                productosPedidos.setPedido(pedido);
                productosPedidos.setCantidad(productoDTO.getCantidad());

                pedido.getProductos().add(productoDTO);
            }
        }
        pedidoRepository.save(pedido);
    }

}