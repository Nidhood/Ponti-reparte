package javeriana.pontireparte.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name ="informacionpagos")
public class InformacionPago {

    @Id
    @GeneratedValue
    private UUID id;
    private String nombretitular;
    private Integer pin;
    private String tipotarjeta;
    private String tipopago;

    // Getters & setters:


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombretitular() {
        return nombretitular;
    }

    public void setNombretitular(String nombretitular) {
        this.nombretitular = nombretitular;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getTipotarjeta() {
        return tipotarjeta;
    }

    public void setTipotarjeta(String tipotarjeta) {
        this.tipotarjeta = tipotarjeta;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }
}
