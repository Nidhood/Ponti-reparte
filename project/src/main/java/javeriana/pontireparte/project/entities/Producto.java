package javeriana.pontireparte.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import  javax.persistence.JoinColumn;
import java.util.UUID;


@Entity
@Table( name ="productos")
public class Producto {

    @Id
    @GeneratedValue
    private UUID id;

    // Cambiado de UUID fotoid a Foto foto
    @OneToOne
    @JoinColumn(name = "fotoid")
    private Foto foto;
    private String nombreproducto;
    private Float preciodinero;
    private Float preciopuntos;
    private String descripcion;
    private Float promocion;
    private String disponibleconpuntos;

    // Getters & setters:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
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

    // ToString:
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", foto=" + foto +
                ", nombreproducto='" + nombreproducto + '\'' +
                ", preciodinero=" + preciodinero +
                ", preciopuntos=" + preciopuntos +
                ", descripcion='" + descripcion + '\'' +
                ", promocion=" + promocion +
                ", disponibleconpuntos='" + disponibleconpuntos + '\'' +
                '}';
    }
}
