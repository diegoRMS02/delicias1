package com.example.delicias.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.delicias.models.Usuario;
import com.example.delicias.service.RolesService;
import com.example.delicias.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolesService rolesService;

    @GetMapping("/listarTodo")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerTodosLosUsuarios());
        return "usuarios/listarTodo";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolesService.obtenerTodosLosRoles());

        return "usuarios/crearUsuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuarios/listarTodo";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolesService.obtenerTodosLosRoles());
        return "usuarios/editarUsuario";
    }

    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.actualizarUsuario(usuario);
        return "redirect:/usuarios/listarTodo";
    }
    
    @GetMapping("/{id}/eliminar")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios/listarTodo";
    }
}