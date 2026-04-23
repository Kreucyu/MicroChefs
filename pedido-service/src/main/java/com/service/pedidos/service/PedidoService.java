package com.service.pedidos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.service.pedidos.dto.*;
import com.service.pedidos.entities.FormaDePagamento;
import com.service.pedidos.entities.ItemPedido;
import com.service.pedidos.entities.Pedido;
import com.service.pedidos.entities.StatusPedido;
import com.service.pedidos.exceptions.ErroPedidoException;
import com.service.pedidos.producer.PedidoProducer;
import com.service.pedidos.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.annotation.JsonAppend;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoProducer pedidoProducer;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public CreatePedidoDto criarPedido(CreatePedidoDto createPedidoDto) {
        Pedido pedido = new Pedido();
        pedido.setClienteId(createPedidoDto.getClienteId());
        pedido.setDataDoPedido(LocalDate.now());
        pedido.setStatusDoPedido(StatusPedido.CRIADO);
        pedido.setFormaDePagamento(createPedidoDto.getFormaDePagamento());

        for(CreateItemPedidoDto itensDto : createPedidoDto.getItens()) {
            ItemPedido itemPedido = new ItemPedido(
                    itensDto.getQuantidadeProduto(),
                    itensDto.getPrecoProduto(),
                    itensDto.getIdProduto(),
                    pedido
            );
            pedido.adicionarItem(itemPedido);
        }
        if(pedido.getFormaDePagamento().equals(FormaDePagamento.DINHEIRO)) {
            pedido.setStatusDoPedido(StatusPedido.PAGO);
            enviarPedidoParaCozinha(pedido);
        }
        pedidoRepository.save(pedido);
        return createPedidoDto;
    }

    public RecoveryPedidoDto exibirPedidoId(Long id) {
        Pedido pedido = this.pedidoRepository.findById(id).get();
        return new RecoveryPedidoDto(pedido.getId(),
                pedido.getStatusDoPedido(),
                pedido.getDataDoPedido(),
                pedido.getFormaDePagamento(),
                new RecoveryItemPedidoDto(pedido.getItens()),
                pedido.getValorTotal());
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

    public UpdatePedidoDto atualizarStatusPedido(Long id, UpdatePedidoDto updatePedidoDto) {
        Pedido pedido = this.pedidoRepository.findById(id).get();
        pedido.setStatusDoPedido(updatePedidoDto.getStatusPedido());
        if(pedido.getStatusDoPedido().equals(StatusPedido.PAGO)) {
            enviarPedidoParaCozinha(pedido);
        }
        pedidoRepository.save(pedido);
        return updatePedidoDto;
    }

    private void enviarPedidoParaCozinha(Pedido pedido) {
        pedidoProducer.enviarParaCozinha(pedido);
    }
}
