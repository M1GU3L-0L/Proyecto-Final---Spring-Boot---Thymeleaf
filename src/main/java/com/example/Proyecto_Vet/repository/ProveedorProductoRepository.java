package com.example.Proyecto_Vet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Proyecto_Vet.model.ProveedorProducto;

@Repository
public interface ProveedorProductoRepository extends JpaRepository<ProveedorProducto, Integer> {
    List<ProveedorProducto> findByProveedor_IdProveedor(Integer idProveedor);
    List<ProveedorProducto> findByProducto_IdProducto(Integer idProducto);
}
