package javeriana.pontireparte.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table( name ="usuarios")
public class Usuario {

    @Id
    @GeneratedValue
    private UUID id;
    private Integer idjaveriana;
    private String nombreusuario;
    private UUID fotoid;
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

    // Constructors:

    public Usuario() {} // Default constructor
    public Usuario(UUID id, Integer idjaveriana, String nombreusuario, UUID fotoid, String tipousuario, String contrasena, String correoinstitucional, String nombre, String apellido, Integer edad, String telefono, String correo, String estadosesion, Integer puntos, Double calificacion, String disponibilidad) {
        this.id = id;
        this.idjaveriana = idjaveriana;
        this.nombreusuario = nombreusuario;
        this.fotoid = fotoid;
        this.tipousuario = tipousuario;
        this.contrasena = contrasena;
        this.correoinstitucional = correoinstitucional;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.estadosesion = estadosesion;
        this.puntos = puntos;
        this.calificacion = calificacion;
        this.disponibilidad = disponibilidad;
    }

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

    public UUID getFotoid() {
        return fotoid;
    }

    public void setFotoid(UUID fotoid) {
        this.fotoid = fotoid;
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
}
