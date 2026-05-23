package com.example.Proyecto_Vet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProyectoVetApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoVetApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("  Servidor corriendo en localhost");
        System.out.println("  http://localhost:8080");
        System.out.println("========================================");
        System.out.println();
    }
}
