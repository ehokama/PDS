package com.example.ConcesionariaPDS.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ConcesionariaPDS.models.Usuarios.Usuario;
import com.example.ConcesionariaPDS.repositories.UsuarioRepositorio;

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

    @GetMapping("/{dni}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String dni){
        Optional<Usuario> opt = repositorio.findById(dni);  
        if(opt.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(opt.get());
        }
    }

    @GetMapping //este método se ejecuta cuando alguien hace un GET a la URL establecida en RequestMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> lista = repositorio.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
