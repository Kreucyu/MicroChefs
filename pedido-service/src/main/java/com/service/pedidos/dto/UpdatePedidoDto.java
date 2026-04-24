package com.service.pedidos.dto;

import com.service.pedidos.entities.StatusPedido;

public record UpdatePedidoDto(
        long id,
        StatusPedido statusPedido
) {
}
