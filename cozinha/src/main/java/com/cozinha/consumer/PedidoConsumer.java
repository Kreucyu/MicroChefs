package com.cozinha.consumer;

import com.cozinha.service.CozinhaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    @Autowired
    private CozinhaService cozinhaService;

    @RabbitListener(queues = { "cozinha-queue" })
    public void receberPedido(@Payload Message message) {
        cozinhaService.realizarPedido(message);
    }
}
