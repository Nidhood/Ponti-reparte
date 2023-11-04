package javeriana.pontireparte.project.entities;

import javeriana.pontireparte.project.dto.ProductoDTO;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name ="productospedidos")
public class ProductoPedido {

    @EmbeddedId
    private ProductoPedidoId id;
    private int cantidad;

    // Getters & setters:
    public ProductoPedidoId getId() {
        return id;
    }

    public void setId(ProductoPedidoId id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
