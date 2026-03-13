package com.service.pedidos.service;

import com.service.pedidos.dto.CreatePedidoDto;
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

    public RecoveryPedidoDto criarPedido(CreatePedidoDto createPedidoDto) {
        Pedido pedido = modelMapper.map(createPedidoDto, Pedido.class);
        pedido.getItens().forEach(item -> item.associarAoPedido(pedido));
        Pedido pedidoSalvar = pedidoRepository.save(pedido);
        return exibirDto(pedidoSalvar);
    }

    private RecoveryPedidoDto exibirDto(Pedido pedido) {
        return modelMapper.map(pedido, RecoveryPedidoDto.class);
    }

    public RecoveryPedidoDto exibirPedidoId(Long id) {
        Pedido pedido = this.pedidoRepository.findById(id).get();
        return modelMapper.map(pedido, RecoveryPedidoDto.class);
    }

    public List<RecoveryPedidoDto> exibirTodosPedidos() {
        List<Pedido> pedidos = this.pedidoRepository.findAll();
        return pedidos.stream().map(pedido -> modelMapper.map(pedido, RecoveryPedidoDto.class)).toList();
    }

    public String deletarPedidoId(Long id) {
        Pedido pedido = this.pedidoRepository.findById(id).get();
        this.pedidoRepository.delete(pedido);
        return "Pedido deletado com sucesso";
    }
}
