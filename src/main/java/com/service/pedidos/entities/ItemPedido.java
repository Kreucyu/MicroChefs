package com.service.pedidos.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
    @Column(name = "id_pedido",  nullable = false)
    private Long _idPedido;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    public ItemPedido(Long id, Long idPedido, Integer quantidade) {
    this._id = id;
    this._idPedido = idPedido;
    this.quantidade = quantidade;
    }

    public ItemPedido() {

    }

    public Long getId() {
        return _id;
    }

    public Long getIdPedido() {
        return _idPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
