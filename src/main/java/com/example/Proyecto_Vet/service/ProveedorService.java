package com.example.Proyecto_Vet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Proyecto_Vet.model.Proveedor;
import com.example.Proyecto_Vet.repository.ProveedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository repo;

    public List<Proveedor> findAll() {
        return repo.findAll();
    }

    public Proveedor findById(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado: " + id));
    }

    public Proveedor save(Proveedor proveedor) {
        return repo.save(proveedor);
    }

    public Proveedor update(Integer id, Proveedor datos) {
        Proveedor p = findById(id);
        p.setNombre(datos.getNombre());
        p.setTelefono(datos.getTelefono());
        return repo.save(p);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

