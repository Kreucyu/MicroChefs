package com.service.pedidos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    @Column(nullable = false)
    private Long idProduto;

    @Column(nullable = false)
    private Integer quantidadeProduto;

    @Column(nullable = false)
    private BigDecimal precoProduto;

    public ItemPedido(Integer quantidadeProduto, BigDecimal precoProduto, Long idProduto, Pedido pedido) {
        this.quantidadeProduto = quantidadeProduto;
        this.precoProduto = precoProduto;
        this.idProduto = idProduto;
        this.pedido = pedido;
    }

    public ItemPedido() {}

    public Long getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

}
