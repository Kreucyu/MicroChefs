package com.service.pedidos.consumer;

import com.service.pedidos.dto.UpdatePedidoDto;
import com.service.pedidos.service.PedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@EnableRetry
public class UpdatePedidoConsumer {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = { "pedido-queue" })
    public void receberAtualizacao(@Payload String updateJson) {
        UpdatePedidoDto update = objectMapper.readValue(updateJson, UpdatePedidoDto.class);
        pedidoService.atualizarStatusPedido(update);
    }

}
