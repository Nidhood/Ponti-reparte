package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.dto.PedidoRequestDTO;
import javeriana.pontireparte.project.entities.PedidoInformacionPago;
import javeriana.pontireparte.project.entities.PedidoInformacionPagoId;
import javeriana.pontireparte.project.repositories.InformacionPagoRepository;
import javeriana.pontireparte.project.repositories.PedidoInformacionPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class PedidoInformacionPagoService {
    // Funciona
    private final PedidoInformacionPagoRepository pedidoInformacionPagoRepository;
    private final PedidoService pedidoService;
    private final InformacionPagoRepository informacionPagoRepository;

    @Autowired
    public PedidoInformacionPagoService(PedidoInformacionPagoRepository pedidoInformacionPagoRepository, PedidoService pedidoService, InformacionPagoRepository informacionPagoRepository) {
        this.pedidoInformacionPagoRepository = pedidoInformacionPagoRepository;
        this.pedidoService = pedidoService;
        this.informacionPagoRepository = informacionPagoRepository;
    }
    public void insertarPedidoInformacionPago(UUID pedidoCreadoId, PedidoRequestDTO pedidoRequestDTO) {
        PedidoInformacionPago pedidoInformacionPago = new PedidoInformacionPago();
        PedidoInformacionPagoId pedidoInformacionPagoId = new PedidoInformacionPagoId();
        pedidoInformacionPagoId.setPedido(pedidoService.infoWithPedido(pedidoCreadoId));
        switch (pedidoRequestDTO.getTipopago()) {
            case "Efectivo":
                pedidoInformacionPagoId.setInformacionPago(informacionPagoRepository.findInformacionPagoById(UUID.fromString("f45e17d9-0627-4e78-a4c7-714d9daff565")));
                break;
            case "Tarjeta":
                pedidoInformacionPagoId.setInformacionPago(informacionPagoRepository.findInformacionPagoById(UUID.fromString("c3afb527-111e-4731-af67-3cd35b759cfd")));
                break;
            case "PontiPuntos":
                pedidoInformacionPagoId.setInformacionPago(informacionPagoRepository.findInformacionPagoById(UUID.fromString("67985675-79fb-4bda-b7c3-c5cf79f358c8")));
                break;
        }
        pedidoInformacionPago.setId(pedidoInformacionPagoId);
        pedidoInformacionPago.setTipodepago(pedidoRequestDTO.getTipopago());
        pedidoInformacionPago.setAclaraciones(pedidoRequestDTO.getAclaraciones());
        pedidoInformacionPago.setPropina(pedidoRequestDTO.getPropina());
        pedidoInformacionPagoRepository.save(pedidoInformacionPago);
    }
}
