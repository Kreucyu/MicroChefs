package com.service.pedidos.dto;


import java.math.BigDecimal;

public record RecoveryItemPedidoDto(
         Long idProduto,
         Integer quantidadeProduto,
         BigDecimal precoProduto
) {

}
