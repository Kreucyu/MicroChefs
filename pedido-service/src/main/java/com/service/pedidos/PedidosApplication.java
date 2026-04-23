package com.service.pedidos;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//@EnableScheduling
@EnableRabbit
@SpringBootApplication
public class PedidosApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PedidosApplication.class, args);
	}

}
