package javeriana.pontireparte.project.entities;


import javeriana.pontireparte.project.dto.ProductoDTO;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name ="tiendas")
public class Tienda {

    @Id
    @GeneratedValue
    private UUID id;
    private String nombretienda;
    private UUID ubicacionid;

    @OneToOne
    @JoinColumn(name = "fotoid")
    private Foto foto;
    private String estadotienda;

    @OneToMany(mappedBy = "tienda")
    private List<TiendaProducto> productos;

    // Getters & setters:
    public List<ProductoDTO> getProductos() {
        if (productos != null) {
            return productos.stream()
                    .map(TiendaProducto::toDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // o retorna null, dependiendo de tu lógica
        }
    }

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

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public String getEstadotienda() {
        return estadotienda;
    }

    public void setEstadotienda(String estadotienda) {
        this.estadotienda = estadotienda;
    }

    public void setProductos(List<TiendaProducto> productos) {
        this.productos = productos;
    }

    // ToString:
    @Override
    public String toString() {
        return "Tienda{" +
                "id=" + id +
                ", nombretienda='" + nombretienda + '\'' +
                ", ubicacionid=" + ubicacionid +
                ", foto=" + foto +
                ", estadotienda='" + estadotienda + '\'' +
                ", productos=" + productos +
                '}';
    }
}
