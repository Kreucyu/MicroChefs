package com.service.pedidos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    @GetMapping("/teste")
    public String teste() {
        return "testando API";
    }
}
