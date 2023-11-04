package javeriana.pontireparte.project.entities;

import javeriana.pontireparte.project.dto.ProductoDTO;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name ="tiendasproductos")
public class TiendaProducto {

    @EmbeddedId
    private TiendaProductoId id;

    private int cantidaddisponible;

    @ManyToOne
    @MapsId("tienda") // Mapea la relación con la clave primaria compuesta
    @JoinColumn(name = "tiendaid")
    private Tienda tienda;

    // Methods:
    @Transient
    public ProductoDTO toDTO() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombreProducto(this.id.getProducto().getNombreproducto());
        productoDTO.setFoto(this.id.getProducto() .getFoto() != null ? this.id.getProducto().getFoto().getFoto() : null);
        productoDTO.setCantidad(this.cantidaddisponible);
        return productoDTO;
    }


    // Getters & setters:
    public TiendaProductoId getId() {
        return id;
    }

    public void setId(TiendaProductoId id) {
        this.id = id;
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
                ", cantidaddisponible=" + cantidaddisponible +
                '}';
    }
}
