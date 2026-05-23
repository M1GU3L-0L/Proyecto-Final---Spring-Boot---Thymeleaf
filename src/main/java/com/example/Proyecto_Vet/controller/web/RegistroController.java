package com.example.Proyecto_Vet.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Proyecto_Vet.model.Empleado;
import com.example.Proyecto_Vet.service.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/registro")
@RequiredArgsConstructor
public class RegistroController {

    private final EmpleadoService empleadoService;

    @GetMapping
    public String formulario(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "registro";
    }

    @PostMapping
    public String registrar(Empleado empleado, Model model) {
        if (empleado.getUsername() == null || empleado.getUsername().isBlank()) {
            model.addAttribute("error", "El usuario es obligatorio");
            return "registro";
        }
        empleado.setRol("EMPLEADO");
        empleadoService.save(empleado);
        return "redirect:/login?registro=exitoso";
    }
}
