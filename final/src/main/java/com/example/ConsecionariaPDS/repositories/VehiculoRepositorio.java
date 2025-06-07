package com.example.ConsecionariaPDS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ConsecionariaPDS.models.Vehiculos.Entidades.Vehiculo;


@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String>{
    
}