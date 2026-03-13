package com.service.pedidos.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "cliente_id", nullable = false)
    private Long clienteId;

    @Enumerated(EnumType.STRING)
    @Column(name= "status_pedido",  nullable = false)
    private StatusPedido statusDoPedido;

    @Column(name= "data_do_pedido",  nullable = false)
    private LocalDate dataDoPedido;

    @Enumerated(EnumType.STRING)
    @Column(name= "forma_de_pagamento, nullable = false")
    private FormaDePagamento formaDePagamento;

    @OneToMany(mappedBy = "idPedido",  cascade = CascadeType.ALL)
    private List<ItemPedido> itens =  new ArrayList<ItemPedido>();

    public Pedido() {
    }
}
