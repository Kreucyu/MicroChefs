package com.service.pedidos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
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

}
