package com.example.delicias.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.delicias.models.Producto;
import com.example.delicias.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {
    @Autowired
    private ProductoService productoService;

// Listar todos los productos
@GetMapping("/listarTodo")
public ResponseEntity<?> listarProductos() {
    try {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    } catch (Exception e) {
        System.err.println("Error al obtener todos los productos: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener todos los productos.");
    }
}

// Crear un nuevo producto
@PostMapping("/crear")
public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
    try {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        System.out.println("Producto creado exitosamente con ID: " + nuevoProducto.getId());
        return ResponseEntity.ok("Producto creado exitosamente.");
    } catch (Exception e) {
        System.err.println("Error al crear el producto: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el producto.");
    }
}

// Obtener producto por ID
@GetMapping("/{id}")
public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
    try {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        
        if (producto.isPresent()) {
            System.out.println("Producto encontrado con ID: " + id);
            return ResponseEntity.ok(producto.get());
        } else {
            System.err.println("No se encontró el producto con ID: " + id);
            return ResponseEntity.notFound().build();
        }
    } catch (Exception e) {
        System.err.println("Error al obtener el producto con ID: " + id + " - " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el producto.");
    }
}

// Actualizar producto por ID
@PutMapping("/{id}")
public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
    try {
        Optional<Producto> productoExistente = productoService.obtenerProductoPorId(id);
        
        if (productoExistente.isPresent()) {
            producto.setId(id); // Asegurar que el ID del producto a actualizar sea el mismo que el enviado en la URL
            productoService.actualizarProducto(producto);
            System.out.println("Producto actualizado correctamente con ID: " + id);
            return ResponseEntity.ok("Producto actualizado correctamente.");
        } else {
            System.err.println("No se encontró el producto con ID: " + id + " para actualizar.");
            return ResponseEntity.notFound().build();
        }
    } catch (Exception e) {
        System.err.println("Error al actualizar el producto con ID: " + id + " - " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el producto.");
    }
}

// Eliminar producto por ID
@DeleteMapping("/{id}")
public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
    try {
        Optional<Producto> productoExistente = productoService.obtenerProductoPorId(id);
        
        if (productoExistente.isPresent()) {
            productoService.eliminarProducto(id);
            System.out.println("Producto eliminado correctamente con ID: " + id);
            return ResponseEntity.ok("Producto eliminado correctamente.");
        } else {
            System.err.println("No se encontró el producto con ID: " + id + " para eliminar.");
            return ResponseEntity.notFound().build();
        }
    } catch (Exception e) {
        System.err.println("Error al eliminar el producto con ID: " + id + " - " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto.");
    }
}
}
