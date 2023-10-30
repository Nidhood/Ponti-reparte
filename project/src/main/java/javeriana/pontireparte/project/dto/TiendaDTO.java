package javeriana.pontireparte.project.dto;

import javeriana.pontireparte.project.entities.Foto;

import java.util.UUID;

public class TiendaDTO {
    private UUID id;
    private String nombreTienda;
    private String foto;
    private int cantidad;

    // Gettter & Setters:


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
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
}
