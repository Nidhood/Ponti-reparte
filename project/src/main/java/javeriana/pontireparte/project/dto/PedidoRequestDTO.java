package javeriana.pontireparte.project.dto;

import java.util.List;
import java.util.UUID;

public class PedidoRequestDTO {
    private UUID compradorid;
    private UUID tiendaid;
    private String tipopedido;
    private Float valortotal;
    private String aclaraciones;
    private Float propina;
    private String tipopago;
    private String tipotarjeta;
    private List<ProductoRequestDTO> productos;
    private UUID ubicacionid;


    // Getters & setters:
    public UUID getCompradorid() {
        return compradorid;
    }

    public void setCompradorid(UUID compradorid) {
        this.compradorid = compradorid;
    }

    public UUID getTiendaid() {
        return tiendaid;
    }

    public void setTiendaid(UUID tiendaid) {
        this.tiendaid = tiendaid;
    }

    public String getTipopedido() {
        return tipopedido;
    }

    public void setTipopedido(String tipopedido) {
        this.tipopedido = tipopedido;
    }

    public Float getValortotal() {
        return valortotal;
    }

    public void setValortotal(Float valortotal) {
        this.valortotal = valortotal;
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

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public String getTipotarjeta() {
        return tipotarjeta;
    }

    public void setTipotarjeta(String tipotarjeta) {
        this.tipotarjeta = tipotarjeta;
    }

    public UUID getUbicacionid() {
        return ubicacionid;
    }

    public void setUbicacionid(UUID ubicacionid) {
        this.ubicacionid = ubicacionid;
    }

    public List<ProductoRequestDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoRequestDTO> productos) {
        this.productos = productos;
    }

    // ToString:
    @Override
    public String toString() {
        return "PedidoRequestDTO{" +
                "compradorid=" + compradorid +
                ", tiendaid=" + tiendaid +
                ", tipopedido='" + tipopedido + '\'' +
                ", valortotal=" + valortotal +
                ", aclaraciones='" + aclaraciones + '\'' +
                ", propina=" + propina +
                ", tipopago='" + tipopago + '\'' +
                ", tipotarjeta='" + tipotarjeta + '\'' +
                ", productos=" + productos +
                ", ubicacionid=" + ubicacionid +
                '}';
    }
}
