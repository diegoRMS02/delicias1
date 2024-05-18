package com.example.delicias.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.delicias.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

