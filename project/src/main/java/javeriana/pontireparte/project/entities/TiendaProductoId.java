package javeriana.pontireparte.project.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TiendaProductoId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "tiendaid")
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name = "productoid")
    private Producto producto;

    // Getters & setters:
    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // Equals & hashCode:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TiendaProductoId that = (TiendaProductoId) o;

        if (tienda != null ? !tienda.equals(that.tienda) : that.tienda != null) return false;
        return producto != null ? producto.equals(that.producto) : that.producto == null;
    }

    @Override
    public int hashCode() {
        int result = tienda != null ? tienda.hashCode() : 0;
        result = 31 * result + (producto != null ? producto.hashCode() : 0);
        return result;
    }

    // ToString:
    @Override
    public String toString() {
        return "TiendaProductoId{" +
                "tienda=" + tienda +
                ", producto=" + producto +
                '}';
    }
}
