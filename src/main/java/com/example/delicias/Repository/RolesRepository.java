package com.example.delicias.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.delicias.models.Roles;
@Repository
public interface RolesRepository extends JpaRepository <Roles, Long>{
    
}
