package javeriana.pontireparte.project.entities;
import javeriana.pontireparte.project.dto.ProductoDTO;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name ="pedidos")
public class Pedido {
    @Id
    @GeneratedValue
    private UUID id;
    private  Integer numeropedido;
    @OneToOne
    @JoinColumn(name = "compradorid")
    private Usuario comprador;

    @OneToOne
    @JoinColumn(name = "repartidorid")
    private Usuario repartidor;

    @OneToOne
    @JoinColumn(name = "tiendaid")
    private Tienda tienda;

    @OneToOne
    @JoinColumn(name = "estadopedidoid")
    private EstadoPedido estadopedido;

    @OneToOne
    @JoinColumn(name = "ubicacionid")
    private Ubicacion ubicacion;
    private  String tipopedido;
    private Float valortotal;

    // Getters & setters:
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumeropedido() {
        return numeropedido;
    }

    public void setNumeropedido(Integer numeropedido) {
        this.numeropedido = numeropedido;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario compradorid) {
        this.comprador = compradorid;
    }

    public Usuario getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Usuario repartidorid) {
        this.repartidor = repartidorid;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tiendaid) {
        this.tienda = tiendaid;
    }

    public EstadoPedido getEstadopedido() {
        return estadopedido;
    }

    public void setEstadopedido(EstadoPedido estadopedidoid) {
        this.estadopedido = estadopedidoid;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacionid) {
        this.ubicacion = ubicacionid;
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


    // ToString:
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", numeropedido=" + numeropedido +
                ", comprador=" + comprador +
                ", repartidor=" + repartidor +
                ", tienda=" + tienda +
                ", estadopedido=" + estadopedido +
                ", ubicacion=" + ubicacion +
                ", tipopedido='" + tipopedido + '\'' +
                ", valortotal=" + valortotal +
                '}';
    }
}
