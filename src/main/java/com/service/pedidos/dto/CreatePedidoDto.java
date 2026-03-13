package com.service.pedidos.dto;

import com.service.pedidos.entities.FormaDePagamento;
import com.service.pedidos.entities.StatusPedido;

import java.time.LocalDate;
import java.util.List;

public class CreatePedidoDto {
        private Long clienteId;
        private StatusPedido statusPedido;
        private LocalDate dataDoPedido;
        private FormaDePagamento formaDePagamento;
        private List<CreateItemPedidoDto> itens;
}
