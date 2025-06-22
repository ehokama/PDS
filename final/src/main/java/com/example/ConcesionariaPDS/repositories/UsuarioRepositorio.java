package com.example.ConcesionariaPDS.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ConcesionariaPDS.models.Usuarios.Usuario;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    Usuario findByCorreo(String correo);
    Optional<Usuario> findByDni(String dni);
}