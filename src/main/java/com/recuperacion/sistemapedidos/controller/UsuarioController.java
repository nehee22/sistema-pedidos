package com.recuperacion.sistemapedidos.controller;

import com.recuperacion.sistemapedidos.entity.Usuario;
import com.recuperacion.sistemapedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // GET /api/usuarios - Lista todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // GET /api/usuarios/{id} - Obtiene un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok((Object) usuario))
                .orElse(ResponseEntity.badRequest().body("Usuario no encontrado con ID: " + id));
    }

    // POST /api/usuarios - Crea un nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getEmail() == null) {
            return ResponseEntity.badRequest().body("Los campos nombre y email son obligatorios.");
        }
        usuario.setFechaRegistro(java.time.LocalDateTime.now());
        Usuario guardado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(guardado);
    }
}