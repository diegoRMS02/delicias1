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

import com.example.delicias.models.Categoria;
import com.example.delicias.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {

    @Autowired
    private CategoriaService categoriaService;

    // Listar todas las categorías
    @GetMapping("/listarTodo")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();
        return ResponseEntity.ok(categorias);
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = categoriaService.obtenerCategoriaPorId(id);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok(categoriaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva categoría
    @PostMapping("/crear")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        try {
            Categoria nuevaCategoria = categoriaService.guardarCategoria(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la categoría.");
        }
    }

    // Actualizar una categoría por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaActualizar) {
        Optional<Categoria> categoriaExistente = categoriaService.obtenerCategoriaPorId(id);
        if (categoriaExistente.isPresent()) {
            Categoria categoria = categoriaExistente.get();
            categoria.setNombre(categoriaActualizar.getNombre()); // Actualizar otros campos según sea necesario
            try {
                categoriaService.guardarCategoria(categoria);
                return ResponseEntity.ok("Categoría actualizada correctamente.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la categoría.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        Optional<Categoria> categoriaExistente = categoriaService.obtenerCategoriaPorId(id);
        if (categoriaExistente.isPresent()) {
            try {
                categoriaService.eliminarCategoria(id);
                return ResponseEntity.ok("Categoría eliminada correctamente.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la categoría.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
