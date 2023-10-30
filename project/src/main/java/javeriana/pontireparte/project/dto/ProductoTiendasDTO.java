package javeriana.pontireparte.project.dto;

import java.util.List;
import java.util.UUID;


public class ProductoTiendasDTO {
    private UUID id;
    private String nombreproducto;
    private String foto;
    private Float preciodinero;
    private Float preciopuntos;
    private String descripcion;
    private Float promocion;
    private String disponibleconpuntos;
    private  List<TiendaDTO> tiendas;

    // Gettter & Setters:


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Float getPreciodinero() {
        return preciodinero;
    }

    public void setPreciodinero(Float preciodinero) {
        this.preciodinero = preciodinero;
    }

    public Float getPreciopuntos() {
        return preciopuntos;
    }

    public void setPreciopuntos(Float preciopuntos) {
        this.preciopuntos = preciopuntos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPromocion() {
        return promocion;
    }

    public void setPromocion(Float promocion) {
        this.promocion = promocion;
    }

    public String getDisponibleconpuntos() {
        return disponibleconpuntos;
    }

    public void setDisponibleconpuntos(String disponibleconpuntos) {
        this.disponibleconpuntos = disponibleconpuntos;
    }

    public List<TiendaDTO> getTiendas() {
        return tiendas;
    }

    public void setTiendas(List<TiendaDTO> tiendas) {
        this.tiendas = tiendas;
    }
}
