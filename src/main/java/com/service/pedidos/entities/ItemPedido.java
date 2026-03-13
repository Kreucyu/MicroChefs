package com.service.pedidos.entities;

import jakarta.persistence.*;

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
    private Integer quantidade;

    public ItemPedido() {
    }
}
