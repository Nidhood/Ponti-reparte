package javeriana.pontireparte.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name ="fotos")
public class Foto {
    @Id
    @GeneratedValue
    private UUID id;
    private String tipofoto;
    private String nombre;
    private  String descripcion;
    private String foto;

    // Getters and setters:
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTipofoto() {
        return tipofoto;
    }

    public void setTipofoto(String tipofoto) {
        this.tipofoto = tipofoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
