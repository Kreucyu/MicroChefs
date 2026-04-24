package com.service.pedidos.dto;

import java.time.LocalDate;
import java.util.List;

public record CozinhaPedidoDto(
        Long id,
        LocalDate dataDoPedido,
        List<CozinhaItemPedidoDto> itens
) {
}
