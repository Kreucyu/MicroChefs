package com.service.pedidos.service;

import com.service.pedidos.entities.NodesInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class BullyService {

        @Value("${process.id}")
        private int processId;

        private boolean isCoordinator = false;

        private final RestTemplate restTemplate = new RestTemplate();

        private final List<NodesInfo> cluster = List.of(
                new NodesInfo(1, "http://localhost:8081"),
                new NodesInfo(2, "http://localhost:8082"),
                new NodesInfo(3, "http://localhost:8083")
        );

        public void startElection() {

            System.out.println("Node " + processId + " iniciou eleição");

            boolean higherNodeAlive = false;

            for (NodesInfo node : cluster) {

                if (node.getId() > processId) {

                    try {
                        restTemplate.postForObject(
                                node.getUrl() + "/bully/election",
                                processId,
                                String.class
                        );

                        higherNodeAlive = true;

                    } catch (Exception ignored) {
                    }
                }
            }

            if (!higherNodeAlive) {
                becomeCoordinator();
            }
        }

        public void receiveElection(int senderId) {

            System.out.println("Node " + processId + " recebeu eleição de " + senderId);

            if (processId > senderId) {

                try {
                    restTemplate.postForObject(
                            "http://localhost:808" + senderId + "/bully/ok",
                            processId,
                            String.class
                    );
                } catch (Exception ignored) {}

                startElection();
            }
        }

        public void becomeCoordinator() {

            isCoordinator = true;

            System.out.println("Node " + processId + " virou COORDENADOR");

            for (NodesInfo node : cluster) {

                if (node.getId() != processId) {

                    try {
                        restTemplate.postForObject(
                                node.getUrl() + "/bully/coordinator",
                                processId,
                                String.class
                        );
                    } catch (Exception ignored) {}
                }
            }
        }

        public void receiveCoordinator(int coordinatorId) {

            isCoordinator = false;

            System.out.println("Node " + processId + " reconhece " + coordinatorId + " como coordenador");
        }

        public boolean isCoordinator() {
            return isCoordinator;
        }
    }
