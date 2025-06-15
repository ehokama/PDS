package com.example.ConsecionariaPDS.models.Usuarios;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="rol_usuario")
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "rol_usuario"
)

@JsonSubTypes({
    @JsonSubTypes.Type(value = Cliente.class, name = "Cliente"),
    @JsonSubTypes.Type(value = Vendedor.class, name = "Vendedor"),
    @JsonSubTypes.Type(value = Administrador.class, name = "Administrador"),
})

public abstract class Usuario {
    @Id
    private String dni;
    
    private String nombre;
    private String password;
    private String correo;
    private String apellido;
    private String telefono;
    
    public Usuario(String dni, String nombre, String apellido, String password, String telefono, String correo) {
        this.dni = dni;
        this.nombre = nombre;
        this.password = password;
        this.correo = correo;
        this.apellido = apellido;
        this.telefono = telefono;
    }
    public Usuario() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario [dni=" + dni + ", nombre=" + nombre + ", password=" + password + ", correo=" + correo
                + ", apellido=" + apellido + ", telefono=" + telefono + "]";
    }
    

}
