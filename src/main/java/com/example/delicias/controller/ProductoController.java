package com.example.delicias.controller;

// ProductoController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.delicias.models.Producto;
import com.example.delicias.service.CategoriaService;
import com.example.delicias.service.ProductoService;
import com.example.delicias.service.UsuarioService;


@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listarTodo")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "productos/listarTodo";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.obtenerTodasLasCategorias());
        model.addAttribute("usuarios", usuarioService.obtenerTodosLosUsuarios());
        return "productos/agregarProducto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos/listarTodo";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarProducto(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoService.obtenerProductoPorId(id));
        model.addAttribute("categorias", categoriaService.obtenerTodasLasCategorias());
        model.addAttribute("usuarios", usuarioService.obtenerTodosLosUsuarios());
        return "productos/editarProducto";
    }
    @PostMapping("/actualizar")
    public String actualizarProducto(Producto producto) {
        productoService.actualizarProducto(producto);
        return "redirect:/productos/listarTodo";
    }
    @GetMapping("/{id}/eliminar")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos/listarTodo";
    }

    // @GetMapping("/{id}/asignar-categoria")
    // public String mostrarFormularioAsignarCategoria(@PathVariable Long id, Model model) {
    //     model.addAttribute("producto", productoService.obtenerProductoPorId(id));
    //     model.addAttribute("categorias", categoriaService.obtenerTodasLasCategorias());
    //     return "productos/asignar-categoria";
    // }

    // @PostMapping("/{id}/asignar-categoria")
    // public String asignarCategoria(@PathVariable Long id, @RequestParam Long categoriaId) {
    //     productoService.asignarCategoria(id, categoriaId);
    //     return "redirect:/productos/" + id + "/editar";
    // }

    // @GetMapping("/{id}/asignar-usuario")
    // public String mostrarFormularioAsignarUsuario(@PathVariable Long id, Model model) {
    //     model.addAttribute("producto", productoService.obtenerProductoPorId(id));
    //     model.addAttribute("usuarios", usuarioService.obtenerTodosLosUsuarios());
    //     return "productos/asignar-usuario";
    // }

    // @PostMapping("/{id}/asignar-usuario")
    // public String asignarUsuario(@PathVariable Long id, @RequestParam Long usuarioId) {
    //     productoService.asignarUsuario(id, usuarioId);
    //     return "redirect:/productos/" + id + "/editar";
    // }
}