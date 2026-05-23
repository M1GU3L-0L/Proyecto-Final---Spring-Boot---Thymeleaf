package com.example.Proyecto_Vet.repository;
import com.example.Proyecto_Vet.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { }

