package com.service.pedidos.dto;

import com.service.pedidos.entities.StatusPedido;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdatePedidoDto {
        private StatusPedido statusPedido;

}
