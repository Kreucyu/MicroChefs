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
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.annotation.JsonAppend;

import java.time.LocalDate;
import java.util.ArrayList;
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
        pedido.setClienteId(createPedidoDto.clienteId());
        pedido.setDataDoPedido(LocalDate.now());
        pedido.setStatusDoPedido(StatusPedido.CRIADO);
        pedido.setFormaDePagamento(createPedidoDto.formaDePagamento());

        for(CreateItemPedidoDto itensDto : createPedidoDto.itens()) {
            ItemPedido itemPedido = new ItemPedido(
                    itensDto.quantidadeProduto(),
                    itensDto.precoProduto(),
                    itensDto.idProduto(),
                    pedido
            );
            pedido.adicionarItem(itemPedido);
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
                pedido.getItens().stream().map(u -> new RecoveryItemPedidoDto(
                        u.getIdProduto(),
                        u.getQuantidadeProduto(),
                        u.getPrecoProduto())).toList(),
                pedido.getValorTotal());
    }

    public List<RecoveryPedidoDto> exibirTodosPedidos() {
        List<Pedido> pedidos = this.pedidoRepository.findAll();
        return pedidos.stream().map(pedido -> new RecoveryPedidoDto(pedido.getId(),
                pedido.getStatusDoPedido(),
                pedido.getDataDoPedido(),
                pedido.getFormaDePagamento(),
                pedido.getItens().stream().map(u -> new RecoveryItemPedidoDto(
                        u.getIdProduto(),
                        u.getQuantidadeProduto(),
                        u.getPrecoProduto())).toList(),
                pedido.getValorTotal())).toList();
    }

    public void deletarPedidoId(Long id) {
        Pedido pedido = this.pedidoRepository.findById(id).get();
        if(pedido == null) { throw new ErroPedidoException("Pedido não encontrado"); }
        this.pedidoRepository.delete(pedido);
    }

    public UpdatePedidoDto atualizarStatusPedido(UpdatePedidoDto updatePedidoDto) {
        Pedido pedido = this.pedidoRepository.findById(updatePedidoDto.id()).get();
        pedido.setStatusDoPedido(updatePedidoDto.statusPedido());
        if(pedido.getStatusDoPedido().equals(StatusPedido.PAGO)) {
            enviarPedidoParaCozinha(new CozinhaPedidoDto(pedido.getId(),
                    pedido.getDataDoPedido(),
                    pedido.getItens()
                            .stream()
                            .map(p -> new CozinhaItemPedidoDto(
                                    p.getIdProduto(),
                                    p.getQuantidadeProduto()))
                            .toList()));
        }
        pedidoRepository.save(pedido);
        return updatePedidoDto;
    }

    private void enviarPedidoParaCozinha(CozinhaPedidoDto pedido) {
        pedidoProducer.enviarParaCozinha(pedido);
    }
}
