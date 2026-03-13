package com.service.pedidos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.pedidos.dto.RecoveryPedidoDto;
import com.service.pedidos.entities.Pedido;
import com.service.pedidos.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private ObjectMapper objectMapper;

    public PedidoController(PedidoService pedidoService, ObjectMapper objectMapper) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @GetMapping
    public ResponseEntity<List<RecoveryPedidoDto>> exibirTodosPedidos() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.exibirTodosPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecoveryPedidoDto> exibirPedido(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.exibirPedidoId(id));
    }
}
