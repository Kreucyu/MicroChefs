package com.service.pedidos.service;

import com.service.pedidos.dto.CreateItemPedidoDto;
import com.service.pedidos.dto.CreatePedidoDto;
import com.service.pedidos.dto.RecoveryPedidoDto;
import com.service.pedidos.dto.UpdatePedidoDto;
import com.service.pedidos.entities.ItemPedido;
import com.service.pedidos.entities.Pedido;
import com.service.pedidos.entities.StatusPedido;
import com.service.pedidos.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public CreatePedidoDto criarPedido(CreatePedidoDto createPedidoDto) {
        Pedido pedido = new Pedido();
        pedido.setClienteId(createPedidoDto.getClienteId());
        pedido.setDataDoPedido(LocalDate.now());
        pedido.setStatusDoPedido(StatusPedido.AGUARDANDO_PAGAMENTO);
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
        pedidoRepository.save(pedido);
        return modelMapper.map(pedido, CreatePedidoDto.class);
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

    public UpdatePedidoDto atualizarStatusPedido(Long id, UpdatePedidoDto updatePedidoDto) {
        Pedido pedido = this.pedidoRepository.findById(id).get();
        pedido.setStatusDoPedido(updatePedidoDto.getStatusPedido());
        return updatePedidoDto;
    }
}
