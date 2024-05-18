package com.example.delicias.controller;

// CategoriaController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.delicias.models.Categoria;
import com.example.delicias.service.CategoriaService;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listarTodo")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.obtenerTodasLasCategorias());
        return "categorias/listarTodo";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/crearCategoria";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/categorias/listarTodo";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarCategoria(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.obtenerCategoriaPorId(id));
        return "categorias/editarCategoria";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias/listarTodo";
    }
}