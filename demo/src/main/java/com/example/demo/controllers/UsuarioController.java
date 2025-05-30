package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Usuarios.Usuario;
import com.example.demo.repositories.UsuarioRepositorio;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class UsuarioController {
    
    UsuarioRepositorio repositorio;

    public UsuarioController(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/api/usuarios")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        if (usuario == null){
            return ResponseEntity.badRequest().build();
        }
        repositorio.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/api/usuarios/login")
    public ResponseEntity<Usuario> login(@RequestBody Map<String, String> loginData) {
        System.out.println("Datos recibidos: " + loginData);
        String correo = loginData.get("correo");
        String password = loginData.get("password");

        Usuario usuario = repositorio.findByCorreo(correo);
        if (usuario != null) {
            System.out.println("Usuario encontrado: " + usuario.getCorreo());
        } else {
            System.out.println("Usuario no encontrado para correo: " + correo);
        }

        if (usuario != null && usuario.getPassword().equals(password)) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
}



    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/api/usuarios")
    public List<Usuario> obtenerUsuarios(){
        return repositorio.findAll();   
    }
    
}
