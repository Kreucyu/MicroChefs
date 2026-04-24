package com.cozinha.dto;

import java.time.LocalDate;
import java.util.List;

public record PedidoRecoveryDto(
        long id,
        LocalDate dataDoPedido,
        List<ItemPedidoRecoveryDto> itens
) {
}
