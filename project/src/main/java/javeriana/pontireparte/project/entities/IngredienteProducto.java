package javeriana.pontireparte.project.entities;

import javeriana.pontireparte.project.dto.IngredienteDTO;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name ="ingredientesproductos")
public class IngredienteProducto {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ingredienteid")
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "productoid")
    private Producto producto;

    private int cantidad;


    /*

       ¿Que hace el decorador @Transient?
        La anotación '@Transient' en Java se emplea en el contexto de persistencia de datos,
        como en Hibernate o JPA, para indicar que un atributo o método de una entidad no debe
        ser almacenado en la base de datos. Al marcar un campo con '@Transient', se señala que
        dicho campo no debe persistirse de forma permanente. Esto resulta útil cuando se tienen
        atributos temporales o métodos que generan información adicional no necesaria para la persistencia.
        En resumen, '@Transient' permite excluir ciertos elementos de una entidad del proceso de
        almacenamiento en la base de datos (muy util en este caso que solo queremos mostrar el nombre del
        ingrediente utilizando el metodo toDTO() ).

     */

    // Methods:
    @Transient
    public IngredienteDTO toDTO() {
        IngredienteDTO ingredienteDTO = new IngredienteDTO();
        ingredienteDTO. setNombreIngrediente(this.ingrediente.getNombreingrediente());
        ingredienteDTO.setCantidad(this.cantidad);
        return ingredienteDTO;
    }

    // Getters & setters:
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
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

    // ToString:
    @Override
    public String toString() {
        return "IngredienteProducto{" +
                "id=" + id +
                ", ingrediente=" + ingrediente +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }
}
