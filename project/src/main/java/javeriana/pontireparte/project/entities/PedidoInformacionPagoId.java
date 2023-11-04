package javeriana.pontireparte.project.entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class PedidoInformacionPagoId implements Serializable {
    @OneToOne
    @JoinColumn(name = "pedidoid")
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "informacionpagosid")
    private InformacionPago informacionPago;

    // Getters & setters:
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public InformacionPago getInformacionPago() {
        return informacionPago;
    }

    public void setInformacionPago(InformacionPago informacionPago) {
        this.informacionPago = informacionPago;
    }

    // Equals & hashCode:
    /*
        Es escencial que dado a la clase compuesta `PedidoInformacionPagoId` no
        ha implementado los métodos `equals` y `hashCode`. Estos métodos son
        esenciales para que Hibernate pueda manejar correctamente la igualdad
        y la comparación de instancias. Al implementar estos métodos en la clase compuesta,
        se proporciona una forma consistente de determinar la igualdad y calcular el hash,
        eliminando los warnings y asegurando un comportamiento adecuado en el manejo
        de la clave compuesta en las entidades Hibernate.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PedidoInformacionPagoId that = (PedidoInformacionPagoId) o;

        if (pedido != null ? !pedido.equals(that.pedido) : that.pedido != null) return false;
        return informacionPago != null ? informacionPago.equals(that.informacionPago) : that.informacionPago == null;
    }

    @Override
    public int hashCode() {
        int result = pedido != null ? pedido.hashCode() : 0;
        result = 31 * result + (informacionPago != null ? informacionPago.hashCode() : 0);
        return result;
    }

    // ToString:
    @Override
    public String toString() {
        return "PedidoInformacionPagoId{" +
                "pedido=" + pedido +
                ", informacionPago=" + informacionPago +
                '}';
    }
}
