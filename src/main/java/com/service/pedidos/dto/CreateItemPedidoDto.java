package com.service.pedidos.dto;

import java.math.BigDecimal;

public record CreateItemPedidoDto(
        Long idProduto,
        Integer quantidadeProduto,
        BigDecimal precoProduto
) {
}
