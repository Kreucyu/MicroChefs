package com.service.pedidos.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
    @Column(name= "cliente_id", nullable = false, unique = true)
    private Long _clienteId;
    @Column(name= "status_pedido",  nullable = false)
    private StatusPedido _statusDoPedido;
    @Column(name= "data_do_pedido",  nullable = false)
    private LocalDate _dataDoPedido;
    @Column(name= "forma_de_pagamento, nullable = false")
    private FormaDePagamento _formaDePagamento;
    @OneToMany
    private ArrayList<ItemPedido> _itens;



    public Pedido() {


    }
}
