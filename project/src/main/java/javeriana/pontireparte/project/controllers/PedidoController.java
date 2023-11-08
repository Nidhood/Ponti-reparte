package javeriana.pontireparte.project.controllers;

import javeriana.pontireparte.project.dto.PedidoRequestDTO;
import javeriana.pontireparte.project.entities.Pedido;
import javeriana.pontireparte.project.entities.Usuario;
import javeriana.pontireparte.project.services.PedidoInformacionPagoService;
import javeriana.pontireparte.project.services.PedidoService;
import javeriana.pontireparte.project.services.ProductoPedidoService;
import javeriana.pontireparte.project.services.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*") // Permitir todas las solicitudes cruzadas
public class PedidoController {

    private final PedidoService pedidoService;
    private final UsuarioController usuarioService;
    private final PedidoInformacionPagoService pedidoInformacionPagoService;
    private final ProductoPedidoService productoPedidoService;
    private final TiendaService tiendaService;

    @Autowired
    public PedidoController(PedidoService pedidoService, UsuarioController usuarioService, PedidoInformacionPagoService pedidoInformacionPagoService, ProductoPedidoService productoPedidoService, TiendaService tiendaService) {
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
        this.pedidoInformacionPagoService = pedidoInformacionPagoService;
        this.productoPedidoService = productoPedidoService;
        this.tiendaService = tiendaService;
    }

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public UUID crearPedido(@RequestBody PedidoRequestDTO pedidoRequestDTO) {
        UUID pedidoCreateId = pedidoService.insertarPedido(pedidoRequestDTO);
        pedidoInformacionPagoService.insertarPedidoInformacionPago(pedidoCreateId, pedidoRequestDTO);
        productoPedidoService.insertarProductosPedido(pedidoCreateId, pedidoRequestDTO);
        tiendaService.actualizarInventario(pedidoRequestDTO);
        return pedidoCreateId;
    }

    @RequestMapping(value = "/{pedidoId}/domiciliario", method = RequestMethod.GET)
    public Usuario infoDomiciliarioDelPedido(@PathVariable (value = "pedidoId") UUID id){
        Pedido pedido = pedidoService.infoWithPedido(id);
        return usuarioService.infoUsuario(pedido.getRepartidor().getId());
    }

    @RequestMapping(value = "/{pedidoId}", method = RequestMethod.GET)
    public Pedido infoUsuario(@PathVariable (value = "pedidoId") UUID id){
        return pedidoService.infoWithPedido(id);
    }
}