package com.service.pedidos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateItemPedidoDto {
        @NotNull
        private Long idProduto;
        @NotNull
        private Integer quantidadeProduto;
        @NotNull
        private BigDecimal precoProduto;
}
