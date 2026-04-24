package com.cozinha.service;

import com.cozinha.dto.PedidoRecoveryDto;
import com.cozinha.dto.UpdatePedidoDto;
import com.cozinha.entities.StatusPedido;
import com.cozinha.producer.AtualizarPedidoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    @Autowired
    private AtualizarPedidoProducer atualizarPedidoProducer;

    public void realizarPedido(PedidoRecoveryDto pedido) {

        System.out.println(pedido);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        atualizarPedidoProducer.enviarAtualizacao(new UpdatePedidoDto(pedido.id(), StatusPedido.EM_PREPARO));
        System.out.println("Atualizado");

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        atualizarPedidoProducer.enviarAtualizacao(new UpdatePedidoDto(pedido.id(), StatusPedido.PRONTO));
        System.out.println("Atualizado 2");
    }
}
