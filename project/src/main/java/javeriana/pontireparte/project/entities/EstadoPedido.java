package javeriana.pontireparte.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name ="estadospedidos")
public class EstadoPedido {
    @Id
    @GeneratedValue
    private UUID id;

    private String estadopago;
    private String estadoenvio;
    private String estadopedido;

    // Getters and setters:
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEstadopago() {
        return estadopago;
    }

    public void setEstadopago(String estadopago) {
        this.estadopago = estadopago;
    }

    public String getEstadoenvio() {
        return estadoenvio;
    }

    public void setEstadoenvio(String estadoenvio) {
        this.estadoenvio = estadoenvio;
    }

    public String getEstadopedido() {
        return estadopedido;
    }

    public void setEstadopedido(String estadopedido) {
        this.estadopedido = estadopedido;
    }

    //ToString:
    @Override
    public String toString() {
        return "EstadoPedido{" +
                "id=" + id +
                ", estadopago='" + estadopago + '\'' +
                ", estadoenvio='" + estadoenvio + '\'' +
                ", estadopedido='" + estadopedido + '\'' +
                '}';
    }
}
