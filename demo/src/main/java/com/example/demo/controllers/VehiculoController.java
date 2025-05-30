package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Vehiculos.Vehiculo;
import com.example.demo.repositories.VehiculoRepositorio;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class VehiculoController {
    
    VehiculoRepositorio repositorio;

    public VehiculoController(VehiculoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/api/vehiculos")
    public List<Vehiculo> obtenerVehiculos(){
        return repositorio.findAll();   
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/api/vehiculos/{patente}")
    public ResponseEntity<Vehiculo> obtenerVehiculo(@PathVariable String patente){
        Optional<Vehiculo> opt = repositorio.findById(patente);  
        if(opt.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(opt.get());
        }
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/api/vehiculos")
    public ResponseEntity<Vehiculo> guardarVehiculo(@RequestBody Vehiculo vehiculo){
        if (vehiculo == null){
            return ResponseEntity.badRequest().build();
        }
        repositorio.save(vehiculo);
        return ResponseEntity.ok(vehiculo);
    }
    
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping("/api/vehiculos")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@RequestBody Vehiculo vehiculo){
        if (vehiculo == null || repositorio.existsById(vehiculo.getPatente())){
            return ResponseEntity.badRequest().build();
        }
        repositorio.save(vehiculo);
        return ResponseEntity.ok(vehiculo);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping("/api/vehiculos/{patente}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable String patente){
        if (patente == null || repositorio.existsById(patente)){
            return ResponseEntity.badRequest().build();
        }
        repositorio.deleteById(patente);
        return ResponseEntity.noContent().build();
    }
}
