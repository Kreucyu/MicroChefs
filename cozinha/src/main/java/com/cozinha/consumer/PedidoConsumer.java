package com.cozinha.consumer;

import com.cozinha.dto.PedidoRecoveryDto;
import com.cozinha.service.CozinhaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@EnableRet
public class PedidoConsumer {

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = { "cozinha-queue" })
    public void receberPedido(@Payload String pedidoJson) {
        PedidoRecoveryDto pedido = objectMapper.readValue(pedidoJson, PedidoRecoveryDto.class);
        cozinhaService.realizarPedido(pedido);
    }
}
