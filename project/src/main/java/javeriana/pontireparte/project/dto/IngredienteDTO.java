package javeriana.pontireparte.project.dto;

/*
 ¿Que es un DTO?

 -> DTO (Data Transfer Object) es un patrón de diseño que se utiliza para transferir datos entre subsistemas de un software. La idea principal es crear un objeto que transporta datos entre las capas de una aplicación en lugar de utilizar directamente los objetos de la capa de datos.

¿Características clave de un DTO?
1. Transferencia de Datos: Su principal responsabilidad es transferir datos entre componentes de un sistema.
2. Sin Lógica de Negocio: No contiene lógica de negocio y generalmente solo tiene campos para almacenar datos.
3. Independiente de la Capa: Es independiente de la capa en la que se utiliza, lo que significa que puede atravesar capas como la de presentación, lógica de negocio y acceso a datos.

¿Por qué se utiliza un DTO?
-> Reducir el Acoplamiento: Ayuda a reducir el acoplamiento entre las capas de una aplicación, ya que los objetos de dominio no se exponen directamente a las capas superiores.
-> Optimizar la Transferencia de Datos: Permite enviar solo la información necesaria, optimizando la transferencia de datos, especialmente en entornos distribuidos.
-> Segregar Responsabilidades: Facilita la segregación de responsabilidades al mantener la lógica de presentación separada de la lógica de negocio y la capa de acceso a datos.
-> Adaptarse a Cambios: Facilita la adaptación a cambios en la estructura interna de las entidades, ya que los cambios en la base de datos no afectan directamente a los DTO.

¿Es considerado un estándar?
-> Aunque el uso de DTOs es una práctica común y recomendada en muchos entornos de desarrollo, no es un estándar formal. La implementación específica puede variar según la arquitectura y las necesidades del proyecto. En muchos casos, frameworks y bibliotecas proporcionan utilidades para simplificar la creación y uso de DTOs.
 */

public class IngredienteDTO {

    private String nombreIngrediente;
    private int cantidad;


    // Getters y Setters:
    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
