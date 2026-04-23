package com.service.pedidos.dto;

import com.service.pedidos.entities.FormaDePagamento;
import com.service.pedidos.entities.ItemPedido;
import com.service.pedidos.entities.StatusPedido;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record RecoveryPedidoDto(
        Long id,
        StatusPedido statusDoPedido,
        LocalDate dataDoPedido,
        FormaDePagamento formaDePagamento,
        List<RecoveryItemPedidoDto>itens,
        BigDecimal valorTotal
) {

}
