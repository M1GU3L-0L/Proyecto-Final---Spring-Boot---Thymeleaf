package com.example.Proyecto_Vet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Proyecto_Vet.model.Producto;
import com.example.Proyecto_Vet.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repo;

    public List<Producto> findAll() {
        return repo.findAll();
    }

    public Producto findById(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }

    public Producto save(Producto producto) {
        return repo.save(producto);
    }

    public Producto update(Integer id, Producto datos) {
        Producto p = findById(id);
        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        return repo.save(p);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

