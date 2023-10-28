package javeriana.pontireparte.project.entities;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ingredientes")
public class Ingrediente {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nombreingrediente")
    private String nombreingrediente;

    // Getters y Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombreingrediente() {
        return nombreingrediente;
    }

    public void setNombreingrediente(String nombreingrediente) {
        this.nombreingrediente = nombreingrediente;
    }

    // ToString:

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nombreingrediente='" + nombreingrediente + '\'' +
                '}';
    }
}

