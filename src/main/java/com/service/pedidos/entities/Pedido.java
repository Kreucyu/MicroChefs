package com.service.pedidos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private BigDecimal valorTotal;

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    @PrePersist
    @PreUpdate
    private void calcularValorTotalDoPedido() {
        this.valorTotal = itens.stream()
                .map(item -> item.getPrecoProduto().multiply(BigDecimal.valueOf((item.getQuantidadeProduto()))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
