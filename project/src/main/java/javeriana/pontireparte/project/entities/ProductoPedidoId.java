package javeriana.pontireparte.project.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

// COMPOSED PRIMARY KEY (super interesante wtf):
@Embeddable
public class ProductoPedidoId implements Serializable {

    @OneToOne
    @JoinColumn(name = "pedidoid")
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "productoid")
    private Producto producto;

    // Getters & setters:
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // Equals y hashCode:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoPedidoId that = (ProductoPedidoId) o;
        return Objects.equals(pedido, that.pedido) &&
                Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, producto);
    }

    // toString:
    @Override
    public String toString() {
        return "ProductoPedidoId{" +
                "pedido=" + pedido +
                ", producto=" + producto +
                '}';
    }
}
