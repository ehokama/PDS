package Facades;
import java.util.ArrayList;
import java.util.List;


import Usuarios.Administrador;
import Usuarios.Cliente;
import Usuarios.Usuario;
import Usuarios.Vendedor;

public class FacadeUsuarios {

    private List<Usuario> sistemaUsuarios = new ArrayList<>();

    public void mostrarCompradores(){
        for (Usuario usuario : sistemaUsuarios) {
            if (usuario instanceof Cliente ){
                System.out.println(usuario.toString());
            } 
        }
    }

    public void añadirCliente(String dni, String nombre, String password, String correo, String apellido, String telefono){
        
        Usuario user1 = new Cliente(dni, nombre, password, correo, apellido, telefono);
        
        sistemaUsuarios.add(user1);
    }

    public void añadirAdministrador(String dni, String nombre, String apellido, String password, String telefono, String correo){
        
        Usuario user1 = new Administrador(dni, nombre, password, correo, apellido, telefono);
        
        sistemaUsuarios.add(user1);
    }

    public void añadirVendedor(String dni, String nombre, String apellido, String password, String telefono, String correo){
        
        Usuario user1 = new Vendedor(dni, nombre, password, correo, apellido, telefono);
        
        sistemaUsuarios.add(user1);
    }

    public void eliminarUsuario(String dni){
    Usuario usuarioAEliminar = null;

    for (Usuario usuario : sistemaUsuarios) {
        if (usuario.getDni().equals(dni)) {
            usuarioAEliminar = usuario;
            break;
        }
    }

    if (usuarioAEliminar != null) {
        sistemaUsuarios.remove(usuarioAEliminar);
        System.out.println("Usuario con DNI " + dni + " eliminado correctamente.");
    } else {
        System.out.println("No se encontró un usuario con el DNI " + dni);
    }
}

}
