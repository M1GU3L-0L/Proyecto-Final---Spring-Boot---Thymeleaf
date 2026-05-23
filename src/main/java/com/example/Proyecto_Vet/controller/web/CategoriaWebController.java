package com.example.Proyecto_Vet.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_Vet.model.Categoria;
import com.example.Proyecto_Vet.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaWebController {

    private final CategoriaService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", service.findAll());
        return "categorias/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("categoria", service.findById(id));
        return "categorias/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Categoria categoria) {
        if (categoria.getIdCategoria() != null) {
            Categoria existente = service.findById(categoria.getIdCategoria());
            existente.setNombre(categoria.getNombre());
            service.save(existente);
        } else {
            service.save(categoria);
        }
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/categorias";
    }
}
