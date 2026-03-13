package com.service.pedidos.service;

import com.service.pedidos.dto.RecoveryPedidoDto;
import com.service.pedidos.entities.Pedido;
import com.service.pedidos.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido criarPedido(Pedido pedido){
        pedido.getItens().forEach(item -> item.associarAoPedido(pedido));
        Pedido pedidoSalvar = pedidoRepository.save(pedido);
        return pedidoSalvar;
    }

    public RecoveryPedidoDto exibirPedidoId(Long id) {
        Pedido pedido = this.pedidoRepository.findById(id).get();
        return modelMapper.map(pedido, RecoveryPedidoDto.class);
    }

    public List<RecoveryPedidoDto> exibirTodosPedidos() {
        List<Pedido> pedidos = this.pedidoRepository.findAll();
        return pedidos.stream().map(pedido -> modelMapper.map(pedido, RecoveryPedidoDto.class)).toList();
    }
}
