package com.service.pedidos.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido idPedido;

    @Column(nullable = false)
    private Long idProduto;

    @Column(nullable = false)
    private Integer quantidadeProduto;

    @Column(nullable = false)
    private BigDecimal precoProduto;

    public ItemPedido() {
    }
}
