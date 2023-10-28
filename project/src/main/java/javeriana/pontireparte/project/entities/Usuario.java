package javeriana.pontireparte.project.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table( name ="usuarios")
public class Usuario {

    @Id
    @GeneratedValue
    private UUID id;
    private Integer idjaveriana;
    private String nombreusuario;

    // Cambiado de UUID fotoid a Foto foto
    @OneToOne
    @JoinColumn(name = "fotoid")
    private Foto foto;
    private String tipousuario;
    private String contrasena;
    private String correoinstitucional;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String telefono;
    private String estadosesion;
    private Integer puntos;
    private Double calificacion;
    private String disponibilidad;

    // Getters and setters:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getIdjaveriana() {
        return idjaveriana;
    }

    public void setIdjaveriana(Integer idjaveriana) {
        this.idjaveriana = idjaveriana;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoinstitucional() {
        return correoinstitucional;
    }

    public void setCorreoinstitucional(String correoinstitucional) {
        this.correoinstitucional = correoinstitucional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstadosesion() {
        return estadosesion;
    }

    public void setEstadosesion(String estadosesion) {
        this.estadosesion = estadosesion;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    // ToString:
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", idjaveriana=" + idjaveriana +
                ", nombreusuario='" + nombreusuario + '\'' +
                ", foto=" + foto +
                ", tipousuario='" + tipousuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", correoinstitucional='" + correoinstitucional + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", estadosesion='" + estadosesion + '\'' +
                ", puntos=" + puntos +
                ", calificacion=" + calificacion +
                ", disponibilidad='" + disponibilidad + '\'' +
                '}';
    }
}
