package com.example.Proyecto_Vet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Proyecto_Vet.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByCliente_IdCliente(Integer idCliente);
    List<Venta> findByEmpleado_IdEmpleado(Integer idEmpleado);
}
