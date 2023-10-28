package javeriana.pontireparte.project.entities;

import javeriana.pontireparte.project.dto.ProductoDTO;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name ="tiendasproductos")
public class TiendaProducto {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tiendaid")
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name = "productoid")
    private Producto producto;

    private int cantidaddisponible;

    // Methods:
    @Transient
    public ProductoDTO toDTO() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombreProducto(this.producto.getNombreproducto());
        productoDTO.setFoto(this.producto.getFoto() != null ? this.producto.getFoto().getFoto() : null);
        productoDTO.setCantidad(this.cantidaddisponible);
        return productoDTO;
    }


    // Getters & setters:
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public int getCantidaddisponible() {
        return cantidaddisponible;
    }

    public void setCantidaddisponible(int cantidad) {
        this.cantidaddisponible = cantidad;
    }

    // ToString:
    @Override
    public String toString() {
        return "TiendaProducto{" +
                "id=" + id +
                ", tienda=" + tienda +
                ", producto=" + producto +
                ", cantidad=" + cantidaddisponible +
                '}';
    }
}
