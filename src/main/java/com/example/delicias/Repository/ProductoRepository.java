package com.example.delicias.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.delicias.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

