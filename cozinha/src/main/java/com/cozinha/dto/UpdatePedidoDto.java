package com.cozinha.dto;

import com.cozinha.entities.StatusPedido;

public record UpdatePedidoDto(
        long id,
        StatusPedido statusPedido
) {
}
