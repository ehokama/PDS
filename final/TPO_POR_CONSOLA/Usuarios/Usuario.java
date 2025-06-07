package Usuarios;

public abstract class Usuario {
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
