package com.recuperacion.sistemapedidos.controller;

import com.recuperacion.sistemapedidos.dto.PedidoRequestDTO;
import com.recuperacion.sistemapedidos.entity.Pedido;
import com.recuperacion.sistemapedidos.entity.Usuario;
import com.recuperacion.sistemapedidos.repository.PedidoRepository;
import com.recuperacion.sistemapedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // GET /api/pedidos - Lista todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        return ResponseEntity.ok(pedidoRepository.findAll());
    }

    // POST /api/pedidos - Crea un nuevo pedido
    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody PedidoRequestDTO request) {

        // Validación 1: Datos obligatorios
        if (request.getUsuarioId() == null || request.getProducto() == null || request.getCantidad() == null) {
            return ResponseEntity.badRequest()
                    .body("Faltan datos obligatorios: usuarioId, producto, cantidad.");
        }

        // Validación 2: La cantidad debe ser positiva
        if (request.getCantidad() <= 0) {
            return ResponseEntity.badRequest()
                    .body("La cantidad debe ser mayor a 0.");
        }

        // Validación 3: El usuario debe existir
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(request.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("El usuario con ID " + request.getUsuarioId() + " no existe.");
        }

        // Crear el pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuarioOpt.get());
        pedido.setProducto(request.getProducto());
        pedido.setCantidad(request.getCantidad());
        pedido.setEstado("pendiente");
        pedido.setFechaPedido(LocalDateTime.now());

        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedidoGuardado);
    }
}