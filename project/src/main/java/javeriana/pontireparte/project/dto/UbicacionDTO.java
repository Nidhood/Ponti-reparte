package javeriana.pontireparte.project.dto;

import java.util.UUID;

public class UbicacionDTO {

    private UUID id;
    private String nombre;

    //Getters & setters:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
