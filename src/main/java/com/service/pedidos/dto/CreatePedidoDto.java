package com.service.pedidos.dto;

import com.service.pedidos.entities.FormaDePagamento;
import com.service.pedidos.entities.StatusPedido;

import java.time.LocalDate;
import java.util.List;

public record CreatePedidoDto(
        Long clienteId,
        StatusPedido statusPedido,
        LocalDate dataDoPedido,
        FormaDePagamento formaDePagamento,
        List<CreateItemPedidoDto> itens
) {

}
