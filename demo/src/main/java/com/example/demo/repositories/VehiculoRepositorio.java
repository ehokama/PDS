package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Vehiculos.Vehiculo;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String>{
    
}
