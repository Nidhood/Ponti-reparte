package javeriana.pontireparte.project.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name ="tiendas")
public class Tienda {
    @Id
    @GeneratedValue
    private UUID id;
    private String nombretienda;
    private UUID ubicacionid;
    private String estadotienda;

    // Getters & setters:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombretienda() {
        return nombretienda;
    }

    public void setNombretienda(String nombretienda) {
        this.nombretienda = nombretienda;
    }

    public UUID getUbicacionid() {
        return ubicacionid;
    }

    public void setUbicacionid(UUID ubicacionid) {
        this.ubicacionid = ubicacionid;
    }

    public String getEstadotienda() {
        return estadotienda;
    }

    public void setEstadotienda(String estadotienda) {
        this.estadotienda = estadotienda;
    }
}
