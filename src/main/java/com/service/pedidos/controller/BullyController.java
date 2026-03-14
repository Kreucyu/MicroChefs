package com.service.pedidos.controller;

import com.service.pedidos.service.BullyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bully")
public class BullyController {

        private final BullyService bullyService;

        public BullyController(BullyService bullyService) {
            this.bullyService = bullyService;
        }

        @PostMapping("/election")
        public void receiveElection(@RequestBody Integer senderId) {
            bullyService.receiveElection(senderId);
        }

        @PostMapping("/coordinator")
        public void receiveCoordinator(@RequestBody Integer coordinatorId) {
            bullyService.receiveCoordinator(coordinatorId);
        }

}
