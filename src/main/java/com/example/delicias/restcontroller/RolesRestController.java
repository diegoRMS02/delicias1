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

import com.example.delicias.models.Roles;
import com.example.delicias.service.RolesService;

@RestController
@RequestMapping("/api/roles")
public class RolesRestController {
    @Autowired
    private RolesService rolesService;
    // Listar todos los roles
    @GetMapping("/listarTodo")
    public ResponseEntity<List<Roles>> listarRoles() {
        List<Roles> roles = rolesService.obtenerTodosLosRoles();
        return ResponseEntity.ok(roles);
    }

    // Obtener un rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerRolPorId(@PathVariable Long id) {
        Optional<Roles> rolOptional = rolesService.getRoleById(id);
        return rolOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo rol
    @PostMapping("/crear")
    public ResponseEntity<?> crearRol(@RequestBody Roles rol) {
        try {
            Roles nuevoRol = rolesService.saveRole(rol);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el rol.");
        }
    }

    // Actualizar un rol por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRol(@PathVariable Long id, @RequestBody Roles rolActualizar) {
        Optional<Roles> rolExistente = rolesService.getRoleById(id);
        if (rolExistente.isPresent()) {
            Roles rol = rolExistente.get();
            rol.setRoles(rolActualizar.getRoles()); // Actualizar otros campos según sea necesario
            try {
                rolesService.saveRole(rol);
                return ResponseEntity.ok("Rol actualizado correctamente.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el rol.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un rol por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long id) {
        Optional<Roles> rolExistente = rolesService.getRoleById(id);
        if (rolExistente.isPresent()) {
            try {
                rolesService.deleteRoleById(id);
                return ResponseEntity.ok("Rol eliminado correctamente.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el rol.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
