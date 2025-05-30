package com.example.demo.repositories;

import com.example.demo.models.Ordenes.OrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdenDeCompraRepositorio extends JpaRepository<OrdenDeCompra, String> {
}
