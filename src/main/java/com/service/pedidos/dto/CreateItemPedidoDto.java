package com.service.pedidos.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateItemPedidoDto {
        private Long idProduto;
        private Integer quantidadeProduto;
        private BigDecimal precoProduto;
}
