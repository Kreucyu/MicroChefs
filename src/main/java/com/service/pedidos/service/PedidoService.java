package com.service.pedidos.service;

import com.service.pedidos.entities.Pedido;
import com.service.pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido criarPedido(Pedido pedido){
        Pedido pedidoSalvar = pedidoRepository.save(pedido);
        return pedidoSalvar;
    }
}
