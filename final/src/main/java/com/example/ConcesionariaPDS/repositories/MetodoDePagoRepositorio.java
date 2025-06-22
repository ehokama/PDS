package com.example.ConcesionariaPDS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ConcesionariaPDS.models.MetodosDePago.MetodoDePago;

@Repository
public interface MetodoDePagoRepositorio extends JpaRepository<MetodoDePago, String> {
}