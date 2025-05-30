package com.example.demo.repositories;

import com.example.demo.models.Usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    Usuario findByCorreo(String correo);
}
