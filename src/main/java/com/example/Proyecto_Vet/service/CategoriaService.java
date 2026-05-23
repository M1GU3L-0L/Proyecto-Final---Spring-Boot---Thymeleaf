package com.example.Proyecto_Vet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Proyecto_Vet.model.Categoria;
import com.example.Proyecto_Vet.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repo;

    public List<Categoria> findAll() {
        return repo.findAll();
    }

    public Categoria findById(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id));
    }

    public Categoria save(Categoria categoria) {
        return repo.save(categoria);
    }

    public Categoria update(Integer id, Categoria datos) {
        Categoria c = findById(id);
        c.setNombre(datos.getNombre());
        return repo.save(c);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
