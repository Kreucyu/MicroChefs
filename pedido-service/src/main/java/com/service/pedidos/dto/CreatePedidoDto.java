package com.service.pedidos.dto;

import com.service.pedidos.entities.FormaDePagamento;
import com.service.pedidos.entities.StatusPedido;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public record CreatePedidoDto(
        Long clienteId,
        FormaDePagamento formaDePagamento,
        List<CreateItemPedidoDto> itens
) {

}
