package com.service.pedidos.entities;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class Nodes {

    @Getter
    private final int id;
    private boolean isAlive = true;
    private boolean isCoordinator = false;

    private List<Nodes> nodes;

    public Nodes(int id) {
        this.id = id;
    }

    public void setCluster(List<Nodes> nodes) {
        this.nodes = nodes;
    }

    public void startElection() {
        System.out.println("Node ID: " + id + " - Iniciando a eleição");

        List<Nodes> nosValidos = nodes.stream()
                .filter(node -> node.getId() > id && node.isAlive)
                .toList();

        if (nosValidos.isEmpty()) {
            becomeCoordinator();
            return;
        }

        for (Nodes node : nosValidos) {
            System.out.println("Node " + id + " enviou ELECTION para " + node.getId());
            node.receiveElection(id);
        }
    }

    public void receiveElection(int nodeId) {
        System.out.println("Node " + id + " recebeu ELECTION de " + nodeId);

        if (isAlive) {
            System.out.println("Node " + id + " respondeu OK para " + nodeId);
            startElection();
        }
    }

    public void becomeCoordinator() {
        isCoordinator = true;

        System.out.println("Node " + id + " virou o NOVO COORDENADOR");

        List<Nodes> nosVivos = nodes.stream()
                .filter(node -> node.getId() != id && node.isAlive)
                .toList();

        for (Nodes node : nosVivos) {
            node.receiveCoordinator(id);
        }
    }

    public void receiveCoordinator(int newCoordinator) {
        isCoordinator = false;

        System.out.println("Node " + id + " reconhece " + newCoordinator + " como coordenador");
    }
}
