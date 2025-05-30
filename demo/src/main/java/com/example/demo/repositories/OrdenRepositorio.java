package com.example.demo.repositories;

import com.example.demo.models.Pedidos.OrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdenRepositorio extends JpaRepository<OrdenDeCompra, String>{
    
}
