package com.example.delicias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.delicias.models.Pedidos;
import com.example.delicias.models.Producto;
import com.example.delicias.models.Usuario;
import com.example.delicias.service.PedidosService;
import com.example.delicias.service.ProductoService;
import com.example.delicias.service.UsuarioService;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {
    @Autowired
    private PedidosService pedidosService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listarTodo")
    public String getAllPedidos(Model model) {
        List<Pedidos> pedidos = pedidosService.findAll();
        model.addAttribute("pedidos", pedidos);
        return "pedidos/listarTodo";
    }

    @GetMapping("/nuevo")
    public String createPedidoForm(Model model) {
        Pedidos pedido = new Pedidos();
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("pedido", pedido);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("productos", productos);
        return "pedidos/crearPedidos";
    }

    @PostMapping("/guardar")
    public String savePedido(@ModelAttribute("pedido") Pedidos pedidos, @RequestParam Long productoId, @RequestParam Long usuarioId) {
        Producto producto = productoService.obtenerProductoPorId(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        pedidos.setProducto(producto);
        pedidos.setUsuario(usuario);
        pedidosService.save(pedidos);
        return "redirect:/pedidos/listarTodo";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarPedido(@PathVariable Long id, Model model) {
        Pedidos pedido = pedidosService.findById(id);
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        model.addAttribute("pedido", pedido);
        model.addAttribute("productos", productos);
        model.addAttribute("usuarios", usuarios);
        return "pedidos/editarPedidos";
    }

    @PostMapping("/actualizar")
    public String actualizarPedido(@ModelAttribute("pedido") Pedidos pedidos, @RequestParam Long productoId, @RequestParam Long usuarioId) {
        Producto producto = productoService.obtenerProductoPorId(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        pedidos.setProducto(producto);
        pedidos.setUsuario(usuario);
        pedidosService.actualizarPedido(pedidos);
        return "redirect:/pedidos/listarTodo";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable Long id) {
        pedidosService.deleteById(id);
        return "redirect:/pedidos/listarTodo";
    }
}
