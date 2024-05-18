package com.example.delicias.service;

import java.util.List;

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

    public void saveRole(Roles rol) {
        rolesRepository.save(rol);
    }

    public Roles getRoleById(Long id) {
        return rolesRepository.findById(id).orElse(null);
    }
    public Roles actualizarRol(Roles rol) {
        return rolesRepository.save(rol);
    }

    public void deleteRoleById(Long id) {
        rolesRepository.deleteById(id);
    }

}