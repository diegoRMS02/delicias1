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

import com.example.delicias.models.Usuario;
import com.example.delicias.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listarTodo")
    public List<Usuario> listarUsuarios() {
    return usuarioService.obtenerTodosLosUsuarios();   
    }
    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            // Guardar el usuario utilizando el servicio
            usuarioService.guardarUsuario(usuario);

            return ResponseEntity.ok("Usuario creado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario: " + e.getMessage());
        }
    }
        @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        try {
            Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
            
            if (usuario.isPresent()) {
                return ResponseEntity.ok(usuario.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el usuario: " + e.getMessage());
        }
    }

    // Actualizar usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Optional<Usuario> usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
            
            if (usuarioExistente.isPresent()) {
                usuario.setId(id); // Aseguramos que el ID del usuario a actualizar sea el mismo que el enviado en la URL
                usuarioService.actualizarUsuario(usuario);
                return ResponseEntity.ok("Usuario actualizado correctamente.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            Optional<Usuario> usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
            
            if (usuarioExistente.isPresent()) {
                usuarioService.eliminarUsuario(id);
                return ResponseEntity.ok("Usuario eliminado correctamente.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}
