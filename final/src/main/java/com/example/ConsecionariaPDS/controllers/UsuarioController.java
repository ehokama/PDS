package com.example.ConsecionariaPDS.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ConsecionariaPDS.models.Usuarios.Usuario;
import com.example.ConsecionariaPDS.repositories.UsuarioRepositorio;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/usuarios") //el endpoint es la URL mediante la cual se accede al recurso
public class UsuarioController {
    private UsuarioRepositorio repositorio;
    
    public UsuarioController(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping //este método se ejecuta cuando alguien hace un POST a esta URL
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){ //ResponseEntity es una forma de devolver una respuesta HTTP con codigo(Status Code) y cuerpo(JSON con los datos si es un get)
        Usuario guardado = repositorio.save(usuario);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        String correo = datos.get("correo");
        String password = datos.get("password");
    
        Usuario usuario = repositorio.findByCorreo(correo);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

}
