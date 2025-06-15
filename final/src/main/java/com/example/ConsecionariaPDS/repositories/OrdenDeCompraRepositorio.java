package com.example.ConsecionariaPDS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;


@Repository
public interface OrdenDeCompraRepositorio extends JpaRepository<OrdenDeCompra, String>{
}