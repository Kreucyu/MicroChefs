package com.service.pedidos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.pedidos.dto.CreatePedidoDto;
import com.service.pedidos.dto.RecoveryPedidoDto;
import com.service.pedidos.dto.UpdatePedidoDto;
import com.service.pedidos.exceptions.ErroPedidoException;
import com.service.pedidos.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService, ObjectMapper objectMapper) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<CreatePedidoDto> criarPedido(@RequestBody CreatePedidoDto createPedidoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criarPedido(createPedidoDto));
    }

    @GetMapping("/exibir")
    public ResponseEntity<List<RecoveryPedidoDto>> exibirTodosPedidos() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.exibirTodosPedidos());
    }

    @GetMapping("/exibir/{id}")
    public ResponseEntity<RecoveryPedidoDto> exibirPedido(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.exibirPedidoId(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarPedido(@PathVariable Long id) {
        try {
            pedidoService.deletarPedidoId(id);
            return ResponseEntity.ok("Pedido deletado com sucesso");
        } catch (ErroPedidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<UpdatePedidoDto> atualizarStatusPedido(@RequestBody UpdatePedidoDto updatePedidoDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.atualizarStatusPedido(updatePedidoDto));
    }
}
