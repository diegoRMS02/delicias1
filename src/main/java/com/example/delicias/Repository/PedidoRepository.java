package com.example.delicias.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.delicias.models.Pedidos;
@Repository
public interface PedidoRepository extends JpaRepository<Pedidos, Long> {
    
}
