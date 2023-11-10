package javeriana.pontireparte.project.dto;

import java.util.UUID;

public class ProductoDTO {
    private UUID id;
    private String nombreProducto;
    private String foto;
    private int cantidad;

    // Gettter & Setters:
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // To String:
    @Override
    public String toString() {
        return "ProductoDTO{" +
                "id=" + id +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", foto='" + foto + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
