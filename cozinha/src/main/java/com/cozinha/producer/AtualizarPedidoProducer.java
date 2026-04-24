package com.cozinha.producer;

import com.cozinha.dto.UpdatePedidoDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class AtualizarPedidoProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void enviarAtualizacao(UpdatePedidoDto update) {
        amqpTemplate.convertAndSend(
                "pedido-exchange",
                "pedido-key.update",
                objectMapper.writeValueAsString(update)
        );

    }
}
