package com.example.Proyecto_Vet.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_Vet.model.Venta;
import com.example.Proyecto_Vet.service.ClienteService;
import com.example.Proyecto_Vet.service.EmpleadoService;
import com.example.Proyecto_Vet.service.ProductoService;
import com.example.Proyecto_Vet.service.VentaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaWebController {

    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final EmpleadoService empleadoService;
    private final ProductoService productoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaService.findAll());
        return "ventas/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("empleados", empleadoService.findAll());
        model.addAttribute("productos", productoService.findAll());
        return "ventas/formulario";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        model.addAttribute("venta", ventaService.findById(id));
        return "ventas/detalle";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Venta venta) {
        ventaService.save(venta);
        return "redirect:/ventas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        ventaService.delete(id);
        return "redirect:/ventas";
    }
}
