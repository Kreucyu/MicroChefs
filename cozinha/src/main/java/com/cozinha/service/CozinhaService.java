package com.cozinha.service;

import com.cozinha.consumer.PedidoConsumer;
import com.cozinha.producer.AtualizarPedidoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    @Autowired
    private AtualizarPedidoProducer atualizarPedidoProducer;

    public void realizarPedido(PedidoRecoverDTO message) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        atualizarPedidoProducer

    }
}
