package com.cozinha.consumer;

import com.cozinha.dto.PedidoRecoveryDto;
import com.cozinha.exceptions.PedidoIncompletoException;
import com.cozinha.producer.PedidoProducer;
import com.cozinha.service.CozinhaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@EnableRetry
public class PedidoConsumer {

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PedidoProducer pedidoProducer;

    @RabbitListener(queues = { "cozinha-queue" })
    public void receberPedido(@Payload String pedidoJson) {
        PedidoRecoveryDto pedido = objectMapper.readValue(pedidoJson, PedidoRecoveryDto.class);
        if(pedido.id() == 0 || pedido.itens() == null || pedido.dataDoPedido() == null) {
            pedidoProducer.dlqSender(pedidoJson);
            throw new PedidoIncompletoException("Dados incompletos");
        }
        cozinhaService.realizarPedido(pedido);

    }
}
