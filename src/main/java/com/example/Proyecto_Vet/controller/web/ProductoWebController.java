package com.example.Proyecto_Vet.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_Vet.model.Producto;
import com.example.Proyecto_Vet.service.CategoriaService;
import com.example.Proyecto_Vet.service.ProductoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoWebController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        return "productos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", productoService.findById(id));
        model.addAttribute("categorias", categoriaService.findAll());
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        if (producto.getIdProducto() != null) {
            Producto existente = productoService.findById(producto.getIdProducto());
            existente.setNombre(producto.getNombre());
            existente.setPrecio(producto.getPrecio());
            existente.setExistencias(producto.getExistencias());
            existente.setCategoria(producto.getCategoria());
            productoService.save(existente);
        } else {
            productoService.save(producto);
        }
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        productoService.delete(id);
        return "redirect:/productos";
    }
}
