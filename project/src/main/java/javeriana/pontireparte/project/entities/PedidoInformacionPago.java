package javeriana.pontireparte.project.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name ="pedidosinformacionpagos")
public class PedidoInformacionPago {

    @EmbeddedId
    private PedidoInformacionPagoId id;

    private String tipodepago;
    private String aclaraciones;
    private float propina;

    // Getters & setters:
    public PedidoInformacionPagoId getId() {
        return id;
    }

    public void setId(PedidoInformacionPagoId id) {
        this.id = id;
    }

    public String getTipodepago() {
        return tipodepago;
    }

    public void setTipodepago(String tipodepago) {
        this.tipodepago = tipodepago;
    }

    public String getAclaraciones() {
        return aclaraciones;
    }

    public void setAclaraciones(String aclaraiones) {
        this.aclaraciones = aclaraiones;
    }

    public float getPropina() {
        return propina;
    }

    public void setPropina(float propina) {
        this.propina = propina;
    }

    // ToString:

    @Override
    public String toString() {
        return "PedidoInformacionPago{" +
                "id=" + id +
                ", aclaraciones='" + aclaraciones + '\'' +
                ", propina=" + propina +
                '}';
    }
}
