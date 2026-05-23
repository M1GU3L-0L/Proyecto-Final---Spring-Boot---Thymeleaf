package com.example.Proyecto_Vet.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_Vet.model.Proveedor;
import com.example.Proyecto_Vet.service.ProveedorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorWebController {

    private final ProveedorService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores", service.findAll());
        return "proveedores/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("proveedor", service.findById(id));
        return "proveedores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proveedor proveedor) {
        if (proveedor.getIdProveedor() != null) {
            Proveedor existente = service.findById(proveedor.getIdProveedor());
            existente.setNombre(proveedor.getNombre());
            existente.setTelefono(proveedor.getTelefono());
            existente.setCorreoElectronico(proveedor.getCorreoElectronico());
            service.save(existente);
        } else {
            service.save(proveedor);
        }
        return "redirect:/proveedores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/proveedores";
    }
}
