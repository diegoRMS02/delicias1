package com.example.delicias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.delicias.Repository.CategoriaRepository;
import com.example.delicias.Repository.ProductoRepository;
import com.example.delicias.Repository.UsuarioRepository;
import com.example.delicias.models.Categoria;
import com.example.delicias.models.Producto;
import com.example.delicias.models.Usuario;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Producto> obtenerTodosLosProductos() {
            return productoRepository.findAll();
        }

        public Producto guardarProducto(Producto producto) {
            return productoRepository.save(producto);
        }

        public Optional<Producto> obtenerProductoPorId(Long id) {
            return productoRepository.findById(id);
        }

        public void eliminarProducto(Long id) {
            productoRepository.deleteById(id);
        }
        public Producto actualizarProducto(Producto producto) {
            return productoRepository.save(producto);
        }

        public Producto asignarCategoria(Long productoId, Long categoriaId) {
            Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
            producto.getCategorias().add(categoria);
            return productoRepository.save(producto);
        }

        public Producto asignarUsuario(Long productoId, Long usuarioId) {
            Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            producto.setUsuario(usuario);
            return productoRepository.save(producto);
        }
}