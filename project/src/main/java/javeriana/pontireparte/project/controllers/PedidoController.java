package javeriana.pontireparte.project.controllers;

import javeriana.pontireparte.project.dto.PedidoRequestDTO;
import javeriana.pontireparte.project.entities.Pedido;
import javeriana.pontireparte.project.entities.Usuario;
import javeriana.pontireparte.project.services.PedidoService;
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

    @Autowired
    public PedidoController(PedidoService pedidoService, UsuarioController usuarioService) {
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearPedido(@RequestBody PedidoRequestDTO pedidoRequestDTO) {
        try {
            pedidoService.insertarPedido(pedidoRequestDTO);
            return ResponseEntity.ok("Pedido creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el pedido: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/{pedidoId}/domiciliario", method = RequestMethod.GET)
    public Usuario infoDomiciliarioDelPedido(@PathVariable (value = "pedidoId") UUID id){
        Pedido pedido = pedidoService.infoWithPedido(id);
        Usuario domciliarioInfo =  usuarioService.infoUsuario(pedido.getCompradorid().getId());
        return domciliarioInfo;
    }
}