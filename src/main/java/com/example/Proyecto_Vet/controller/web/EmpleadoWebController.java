package com.example.Proyecto_Vet.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_Vet.model.Empleado;
import com.example.Proyecto_Vet.service.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoWebController {

    private final EmpleadoService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empleados", service.findAll());
        return "empleados/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleados/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("empleado", service.findById(id));
        return "empleados/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Empleado empleado) {
        if (empleado.getIdEmpleado() != null) {
            Empleado existente = service.findById(empleado.getIdEmpleado());
            existente.setNombre(empleado.getNombre());
            existente.setPosicion(empleado.getPosicion());
            existente.setUsername(empleado.getUsername());
            existente.setRol(empleado.getRol());
            if (empleado.getPassword() != null && !empleado.getPassword().isBlank()) {
                existente.setPassword(empleado.getPassword());
            }
            service.save(existente);
        } else {
            service.save(empleado);
        }
        return "redirect:/empleados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/empleados";
    }
}
