package com.service.pedidos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

public record CreateItemPedidoDto(
        @NotNull Long idProduto,
        @NotNull Integer quantidadeProduto,
        @NotNull BigDecimal precoProduto
) {
}
