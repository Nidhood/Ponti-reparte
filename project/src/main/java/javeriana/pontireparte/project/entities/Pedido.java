package javeriana.pontireparte.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name ="pedidos")
public class Pedido {
    @Id
    @GeneratedValue
    private UUID id;
    private  String numeropedido;
    private UUID compradorid;
    private UUID repartidorid;
    private UUID tiendaid;
    private UUID estadopedidoid;
    private UUID ubicacionid;
    private  String tipopedido;
    private Float valortotal;

    // Getters & setters:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeropedido() {
        return numeropedido;
    }

    public void setNumeropedido(String numeropedido) {
        this.numeropedido = numeropedido;
    }

    public UUID getCompradorid() {
        return compradorid;
    }

    public void setCompradorid(UUID compradorid) {
        this.compradorid = compradorid;
    }

    public UUID getRepartidorid() {
        return repartidorid;
    }

    public void setRepartidorid(UUID repartidorid) {
        this.repartidorid = repartidorid;
    }

    public UUID getTiendaid() {
        return tiendaid;
    }

    public void setTiendaid(UUID tiendaid) {
        this.tiendaid = tiendaid;
    }

    public UUID getEstadopedidoid() {
        return estadopedidoid;
    }

    public void setEstadopedidoid(UUID estadopedidoid) {
        this.estadopedidoid = estadopedidoid;
    }

    public UUID getUbicacionid() {
        return ubicacionid;
    }

    public void setUbicacionid(UUID ubicacionid) {
        this.ubicacionid = ubicacionid;
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
}
