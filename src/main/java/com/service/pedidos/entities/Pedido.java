package com.service.pedidos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clienteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido statusDoPedido;

    @Column(nullable = false)
    private LocalDate dataDoPedido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaDePagamento formaDePagamento;

    @JsonManagedReference
    @OneToMany(mappedBy = "pedido",  cascade = CascadeType.ALL)
    private List<ItemPedido> itens =  new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal valorTotal = calcularValorTotalDoPedido();

    public Pedido() {
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public StatusPedido getStatusDoPedido() {
        return statusDoPedido;
    }

    public LocalDate getDataDoPedido() {
        return dataDoPedido;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    private BigDecimal calcularValorTotalDoPedido() {
        BigDecimal valorTotal = null;
        for(ItemPedido item : itens) {
            BigDecimal precoDoProduto = item.getPrecoProduto();
            BigDecimal quantidadeDoProduto = BigDecimal.valueOf(item.getQuantidadeProduto());
            valorTotal.add(precoDoProduto.multiply(quantidadeDoProduto));
        }
        return valorTotal;
    }
}
