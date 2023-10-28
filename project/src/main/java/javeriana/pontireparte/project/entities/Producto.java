package javeriana.pontireparte.project.entities;

import javeriana.pontireparte.project.dto.IngredienteDTO;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Entity
@Table( name ="productos")
public class Producto {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "fotoid")
    private Foto foto;
    private String nombreproducto;
    private Float preciodinero;
    private Float preciopuntos;
    private String descripcion;
    private Float promocion;
    private String disponibleconpuntos;

    @OneToMany(mappedBy = "producto")
    private List<IngredienteProducto> ingredientes;

    public List<IngredienteDTO> getIngredientes() {
        if (ingredientes != null) {
            return ingredientes.stream()
                    .map(IngredienteProducto::toDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // o retorna null, dependiendo de tu lógica
        }
    }

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

    public void setIngredientes(List<IngredienteProducto> ingredientesProductos) {
        this.ingredientes = ingredientesProductos;
    }

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
