package backend.Usuarios;

import java.util.List;

public class SistemaUsuarios {
    private List<Usuario> usuarios;

    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void eliminarUsuario(Usuario usuario){
        usuarios.remove(usuario);
    }

    public List<Usuario> getUsuarios(){
        return this.usuarios;
    }

}
