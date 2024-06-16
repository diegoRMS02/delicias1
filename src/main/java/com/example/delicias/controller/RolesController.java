package com.example.delicias.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.delicias.models.Roles;
import com.example.delicias.service.RolesService;


@Controller
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping("/listarTodo")
    public String listRoles(Model model) {
        model.addAttribute("roles", rolesService.obtenerTodosLosRoles());
        return "roles/listarTodo";
    }

    @GetMapping("/nuevo")
    public String newRole(Model model) {
        model.addAttribute("rol", new Roles());
        return "roles/agregarRol";
    }

@PostMapping("/guardar")
    public String saveRole(@RequestParam("roles") String roles) {
    Roles rol = new Roles();
    rol.setRoles(roles);
    rolesService.saveRole(rol);
    return "redirect:/roles/listarTodo";
}

    @GetMapping("/{id}/editar")
    public String editRole(@PathVariable Long id, Model model) {
        Optional<Roles> rol = rolesService.getRoleById(id);
        model.addAttribute("rol", rol);
        return "roles/editarRol";
    }
    // @PostMapping("/actualizar")
    // public String updateRole(@RequestParam("id") Long id, @RequestParam("roles") String roles) {
    //     Roles rol = rolesService.getRoleById(id);
    //     rol.setRoles(roles);
    //     rolesService.actualizarRol(rol);
    //     return "redirect:/roles/listarTodo";     
    // }


    @GetMapping("/{id}/eliminar")
    public String deleteRole(@PathVariable Long id) {
        rolesService.deleteRoleById(id);
        return "redirect:/roles/listarTodo";
    }
}