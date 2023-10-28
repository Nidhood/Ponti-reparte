package javeriana.pontireparte.project.dto;

import java.util.List;
import java.util.UUID;

public class PedidoRequestDTO {
    private UUID compradorId;
    private UUID tiendaId;
    private String tipoPedido;
    private Float valorTotal;
    private String aclaraciones;
    private Float propina;
    private String tipoPago;
    private String tipoTarjeta;
    private UUID ubicacionId;
    private List<ProductoDTO> productos;


    // Getters & setters:
    public UUID getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(UUID compradorId) {
        this.compradorId = compradorId;
    }

    public UUID getTiendaId() {
        return tiendaId;
    }

    public void setTiendaId(UUID tiendaId) {
        this.tiendaId = tiendaId;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getAclaraciones() {
        return aclaraciones;
    }

    public void setAclaraciones(String aclaraciones) {
        this.aclaraciones = aclaraciones;
    }

    public Float getPropina() {
        return propina;
    }

    public void setPropina(Float propina) {
        this.propina = propina;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public UUID getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(UUID ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }
}
