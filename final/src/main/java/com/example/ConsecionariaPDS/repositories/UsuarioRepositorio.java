package com.example.ConsecionariaPDS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ConsecionariaPDS.models.Usuarios.Usuario;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    Usuario findByCorreo(String correo);
}