package com.example.ConcesionariaPDS.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ConcesionariaPDS.models.Vehiculos.Entidades.Vehiculo;


@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String>{

    Optional<Vehiculo> findByPatente(String patenteVehiculo);
}