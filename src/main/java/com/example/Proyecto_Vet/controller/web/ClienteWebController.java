package com.example.Proyecto_Vet.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_Vet.model.Cliente;
import com.example.Proyecto_Vet.service.ClienteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteWebController {

    private final ClienteService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", service.findAll());
        return "clientes/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", service.findById(id));
        return "clientes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente) {
        if (cliente.getIdCliente() != null) {
            Cliente existente = service.findById(cliente.getIdCliente());
            existente.setNombre(cliente.getNombre());
            existente.setTelefono(cliente.getTelefono());
            existente.setCorreoElectronico(cliente.getCorreoElectronico());
            service.save(existente);
        } else {
            service.save(cliente);
        }
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/clientes";
    }
}
