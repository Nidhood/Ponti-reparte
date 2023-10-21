package javeriana.pontireparte.project.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name ="ingredientes")
public class ingrediente {
    @Id
    @GeneratedValue
    private UUID id;
    private  String nombreingrediente;

    // Getters and setters:

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
}
