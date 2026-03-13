package com.service.pedidos.dto;

import com.service.pedidos.entities.FormaDePagamento;
import com.service.pedidos.entities.ItemPedido;
import com.service.pedidos.entities.StatusPedido;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
public class PedidoDto {
    private Long id;
    private StatusPedido statusDoPedido;
    private LocalDate dataDoPedido;
    private FormaDePagamento formaDePagamento;
    private List<ItemPedido> itens;
    private BigDecimal valorTotal;


}
