package javeriana.pontireparte.project.entities;

import javeriana.pontireparte.project.dto.ProductoDTO;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name ="productospedidos")
public class ProductoPedido {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pedidoid")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "productoid")
    private Producto producto;
    private int cantidad;

    // Methods:
    @Transient
    public ProductoDTO toDTO() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombreProducto(this.producto.getNombreproducto());
        productoDTO.setFoto(this.producto.getFoto() != null ? this.producto.getFoto().getFoto() : null);
        productoDTO.setCantidad(this.cantidad);
        return productoDTO;
    }

    // Getters & setters:
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
