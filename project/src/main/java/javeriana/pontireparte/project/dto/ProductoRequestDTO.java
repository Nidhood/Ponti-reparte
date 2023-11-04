package javeriana.pontireparte.project.dto;

import java.util.UUID;

public class ProductoRequestDTO {
    private UUID id;
    private Integer cantidad;

    // Getters & setters:
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    // ToString:
    @Override
    public String toString() {
        return "ProductoRequestDTO{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                '}';
    }
}
