package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.dto.PedidoRequestDTO;
import javeriana.pontireparte.project.dto.ProductoRequestDTO;
import javeriana.pontireparte.project.entities.Pedido;
import javeriana.pontireparte.project.entities.Producto;
import javeriana.pontireparte.project.entities.ProductoPedido;
import javeriana.pontireparte.project.entities.ProductoPedidoId;
import javeriana.pontireparte.project.repositories.ProductoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductoPedidoService {

    private final ProductoPedidoRepository productoPedidoRepository;
    private final PedidoService pedidoService;

    private final ProductoService productoService;

    @Autowired
    public ProductoPedidoService(ProductoPedidoRepository productoPedidoRepository, PedidoService pedidoService, ProductoService productoService) {
        this.productoPedidoRepository = productoPedidoRepository;
        this.pedidoService  = pedidoService;
        this.productoService = productoService;
    }

    public void insertarProductosPedido(UUID pedidoCreadoId, PedidoRequestDTO pedidoRequestDTO) {
        for (ProductoRequestDTO productoRequestDTO : pedidoRequestDTO.getProductos()) {
            ProductoPedido productoPedido = new ProductoPedido();
            ProductoPedidoId productoPedidoId = new ProductoPedidoId();
            productoPedidoId.setPedido(pedidoService.infoWithPedido(pedidoCreadoId));
            productoPedidoId.setProducto(productoService.infoWithProducto(productoRequestDTO.getId()));
            productoPedido.setId(productoPedidoId);
            productoPedido.setCantidad(productoRequestDTO.getCantidad());
            productoPedidoRepository.save(productoPedido);
        }
    }
}
