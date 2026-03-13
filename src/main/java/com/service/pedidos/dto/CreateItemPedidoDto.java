package com.service.pedidos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateItemPedidoDto {
        private Long idProduto;
        private Integer quantidadeProduto;
        private BigDecimal precoProduto;
}
