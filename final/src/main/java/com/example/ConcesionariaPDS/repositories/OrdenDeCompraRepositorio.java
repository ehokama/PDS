package com.example.ConcesionariaPDS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;


@Repository
public interface OrdenDeCompraRepositorio extends JpaRepository<OrdenDeCompra, String>{
}