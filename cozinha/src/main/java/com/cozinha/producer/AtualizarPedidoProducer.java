package com.cozinha.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class AtualizarPedidoProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper;

    public AtualizarPedidoProducer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void enviarAtualizacao(Message message) {
        amqpTemplate.convertAndSend(
                "pedido-exchange",
                "pedido-key.update",
                objectMapper.writeValueAsString(message)
        );

    }
}
