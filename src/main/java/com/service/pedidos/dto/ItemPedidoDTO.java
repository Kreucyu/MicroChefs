package com.service.pedidos.dto;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class ItemPedidoDTO {
    private Long idProduto;
    private Integer quantidadeDoProduto;
    private BigDecimal precoDoProduto;
}
