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
    private UUID compradorid;
    private UUID repartidorid;
    private UUID tiendaid;
    private UUID estadopedidoid;
    private UUID ubicacionid;
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
