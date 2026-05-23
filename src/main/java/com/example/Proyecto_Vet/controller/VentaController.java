package com.example.Proyecto_Vet.controller;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_Vet.model.Venta;
import com.example.Proyecto_Vet.service.VentaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService service;

    @GetMapping
    public List<Venta> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Venta> create(@RequestBody Venta venta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(venta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<@Nullable Object> update(@PathVariable Integer id, @RequestBody Venta venta) {
        return ResponseEntity.ok(service.update(id, venta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}