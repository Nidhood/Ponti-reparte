package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.dto.PedidoRequestDTO;
import javeriana.pontireparte.project.entities.*;
import javeriana.pontireparte.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadoPedidoRepository estadoPedidoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final InformacionPagoRepository informacionPagoRepository;
    private final TiendaService tiendaService;
    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, EstadoPedidoRepository estadoPedidoRepository, UbicacionRepository ubicacionRepository, InformacionPagoRepository informacionPagoRepository, TiendaService tiendaService) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.estadoPedidoRepository = estadoPedidoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.informacionPagoRepository = informacionPagoRepository;
        this.tiendaService = tiendaService;
    }

    public Pedido infoWithPedido(UUID pedidoId) {
        return pedidoRepository.findPedidoById(pedidoId);
    }

    public UUID insertarPedido(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido();
        System.out.println("PedidoRequestDTO: " + pedidoRequestDTO);
        pedido.setNumeropedido(generarNumeroPedidoUnico()); // Generar un número de pedido único de 5 cifras
        pedido.setComprador(usuarioRepository.findUsuarioById(pedidoRequestDTO.getCompradorid()));
        pedido.setRepartidor(usuarioRepository.findByNombreusuario("rp_jones")); // Repartidor por defecto.
        pedido.setTienda(tiendaService.infoWithTienda(pedidoRequestDTO.getTiendaid()));
        pedido.setEstadopedido(estadoPedidoRepository.findEstadoPedidoById(UUID.fromString("70b455c2-470e-4fec-a590-44c48978abcb"))); // Estado por defecto.
        pedido.setUbicacion(ubicacionRepository.findUbicacionById(pedidoRequestDTO.getUbicacionid()));
        pedido.setTipopedido(pedidoRequestDTO.getTipopedido());
        pedido.setValortotal(pedidoRequestDTO.getValortotal());
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        return pedidoGuardado.getId();
    }

    private int generarNumeroPedidoUnico() {
        int nuevoNumeroPedido;
        do {
            nuevoNumeroPedido = (int) (Math.random() * 90000) + 10000;
        } while (pedidoRepository.existsByNumeropedido(nuevoNumeroPedido));
        return nuevoNumeroPedido;
    }
}
