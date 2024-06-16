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

import com.example.delicias.models.Pedidos;
import com.example.delicias.service.PedidosService;
@RestController
@RequestMapping("/api/pedidos")
public class PedidosRestController {
    @Autowired
    private PedidosService pedidosService;

    // Listar todos los pedidos
    @GetMapping("/listarTodo")
    public ResponseEntity<?> listarPedidos() {
        try {
            List<Pedidos> pedidos = pedidosService.findAll();
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            System.err.println("Error al obtener todos los pedidos: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener todos los pedidos.");
        }
    }

    // Crear un nuevo pedido
    @PostMapping("/crear")
    public ResponseEntity<?> crearPedido(@RequestBody Pedidos pedido) {
        try {
            Pedidos nuevoPedido = pedidosService.save(pedido);
            System.out.println("Pedido creado exitosamente con ID: " + nuevoPedido.getId());
            return ResponseEntity.ok("Pedido creado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al crear el pedido: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el pedido.");
        }
    }

    // Obtener pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPedidoPorId(@PathVariable Long id) {
        try {
            Optional<Pedidos> pedido = pedidosService.findById(id);
            
            if (pedido.isPresent()) {
                System.out.println("Pedido encontrado con ID: " + id);
                return ResponseEntity.ok(pedido.get());
            } else {
                System.err.println("No se encontró el pedido con ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el pedido con ID: " + id + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el pedido.");
        }
    }

    // Actualizar pedido por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPedido(@PathVariable Long id, @RequestBody Pedidos pedido) {
        try {
            Optional<Pedidos> pedidoExistente = pedidosService.findById(id);
            
            if (pedidoExistente.isPresent()) {
                pedido.setId(id); // Asegurar que el ID del pedido a actualizar sea el mismo que el enviado en la URL
                pedidosService.actualizarPedido(pedido);
                System.out.println("Pedido actualizado correctamente con ID: " + id);
                return ResponseEntity.ok("Pedido actualizado correctamente.");
            } else {
                System.err.println("No se encontró el pedido con ID: " + id + " para actualizar.");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el pedido con ID: " + id + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el pedido.");
        }
    }

    // Eliminar pedido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        try {
            Optional<Pedidos> pedidoExistente = pedidosService.findById(id);
            
            if (pedidoExistente.isPresent()) {
                pedidosService.deleteById(id);
                System.out.println("Pedido eliminado correctamente con ID: " + id);
                return ResponseEntity.ok("Pedido eliminado correctamente.");
            } else {
                System.err.println("No se encontró el pedido con ID: " + id + " para eliminar.");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar el pedido con ID: " + id + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el pedido.");
        }
    }
}
