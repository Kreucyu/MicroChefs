package com.service.pedidos.dto;

import com.service.pedidos.entities.FormaDePagamento;
import com.service.pedidos.entities.StatusPedido;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreatePedidoDto {
        private Long clienteId;
        private FormaDePagamento formaDePagamento;
        private List<CreateItemPedidoDto> itens;
}
