package com.service.pedidos.dto;

import com.service.pedidos.entities.FormaDePagamento;
import com.service.pedidos.entities.ItemPedido;
import com.service.pedidos.entities.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RecoveryPedidoDto {
        private Long id;
        private StatusPedido statusDoPedido;
        private LocalDate dataDoPedido;
        private FormaDePagamento formaDePagamento
        private List<RecoveryItemPedidoDto>itens;
        private BigDecimal valorTotal;
}
