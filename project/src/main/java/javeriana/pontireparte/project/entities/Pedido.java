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
    private  String numeropedido;

    @OneToOne
    @JoinColumn(name = "compradorid")
    private Usuario compradorid;

    @OneToOne
    @JoinColumn(name = "repartidorid")
    private Usuario repartidorid;

    @OneToOne
    @JoinColumn(name = "tiendaid")
    private Tienda tiendaid;

    @OneToOne
    @JoinColumn(name = "estadopedidoid")
    private EstadoPedido estadopedidoid;

    @OneToOne
    @JoinColumn(name = "ubicacionid")
    private Ubicacion ubicacionid;
    private  String tipopedido;
    private Float valortotal;
    @OneToMany(mappedBy = "pedido")
    private List<ProductoPedido> productos;

    // Getters & setters:
    public List<ProductoDTO> getProductos() {
        if (productos != null) {
            return productos.stream()
                    .map(ProductoPedido::toDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // o retorna null, dependiendo de tu lógica
        }
    }

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

    public Usuario getCompradorid() {
        return compradorid;
    }

    public void setCompradorid(Usuario compradorid) {
        this.compradorid = compradorid;
    }

    public Usuario getRepartidorid() {
        return repartidorid;
    }

    public void setRepartidorid(Usuario repartidorid) {
        this.repartidorid = repartidorid;
    }

    public Tienda getTiendaid() {
        return tiendaid;
    }

    public void setTiendaid(Tienda tiendaid) {
        this.tiendaid = tiendaid;
    }

    public EstadoPedido getEstadopedidoid() {
        return estadopedidoid;
    }

    public void setEstadopedidoid(EstadoPedido estadopedidoid) {
        this.estadopedidoid = estadopedidoid;
    }

    public Ubicacion getUbicacionid() {
        return ubicacionid;
    }

    public void setUbicacionid(Ubicacion ubicacionid) {
        this.ubicacionid = ubicacionid;
    }

    public void setProductos(List<ProductoPedido> productos) {
        this.productos = productos;
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
                ", numeropedido='" + numeropedido + '\'' +
                ", compradorid=" + compradorid +
                ", repartidorid=" + repartidorid +
                ", tiendaid=" + tiendaid +
                ", estadopedidoid=" + estadopedidoid +
                ", ubicacionid=" + ubicacionid +
                ", tipopedido='" + tipopedido + '\'' +
                ", valortotal=" + valortotal +
                '}';
    }
}
