package com.example.delicias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.delicias.Repository.RolesRepository;
import com.example.delicias.models.Roles;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    public List<Roles> obtenerTodosLosRoles() {
        return rolesRepository.findAll();
    }

    public Roles saveRole(Roles rol) {
        return rolesRepository.save(rol);
    }

    public Optional<Roles> getRoleById(Long id) {
        return rolesRepository.findById(id);
    }
    public Roles actualizarRol(Roles rol) {
        return rolesRepository.save(rol);
    }

    public void deleteRoleById(Long id) {
        rolesRepository.deleteById(id);
    }

}