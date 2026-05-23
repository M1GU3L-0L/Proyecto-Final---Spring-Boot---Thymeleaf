package com.example.Proyecto_Vet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Proyecto_Vet.model.Cliente;
import com.example.Proyecto_Vet.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repo;

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente findById(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + id));
    }

    public Cliente save(Cliente cliente) {
        return repo.save(cliente);
    }

    public Cliente update(Integer id, Cliente datos) {
        Cliente c = findById(id);
        c.setNombre(datos.getNombre());
        return repo.save(c);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
