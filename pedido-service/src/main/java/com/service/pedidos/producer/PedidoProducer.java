package com.service.pedidos.producer;

import com.service.pedidos.dto.CozinhaPedidoDto;
import com.service.pedidos.entities.Pedido;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class PedidoProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper;

    public PedidoProducer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void enviarParaCozinha(CozinhaPedidoDto pedido) {
        amqpTemplate.convertAndSend(
                "pedido-exchange",
                "pedido-key.pago",
                objectMapper.writeValueAsString(pedido)
        );
    }
}
