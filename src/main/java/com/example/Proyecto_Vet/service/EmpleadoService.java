package com.example.Proyecto_Vet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Proyecto_Vet.model.Empleado;
import com.example.Proyecto_Vet.repository.EmpleadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository repo;
    private final PasswordEncoder passwordEncoder;

    public Empleado save(Empleado empleado) {
        if (empleado.getPassword() != null && !empleado.getPassword().isBlank()) {
            empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        }
        return repo.save(empleado);
    }

    public List<Empleado> findAll() {
        return repo.findAll();
    }

    public Empleado findById(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado: " + id));
    }

    public Empleado update(Integer id, Empleado datos) {
        Empleado e = findById(id);
        e.setNombre(datos.getNombre());
        return repo.save(e);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

