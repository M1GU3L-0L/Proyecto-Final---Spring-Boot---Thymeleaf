package com.example.Proyecto_Vet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.Proyecto_Vet.model.Categoria;
import com.example.Proyecto_Vet.model.Cliente;
import com.example.Proyecto_Vet.model.Empleado;
import com.example.Proyecto_Vet.model.Producto;
import com.example.Proyecto_Vet.model.Proveedor;
import com.example.Proyecto_Vet.model.ProveedorProducto;
import com.example.Proyecto_Vet.model.Venta;
import com.example.Proyecto_Vet.model.DetalleVenta;
import com.example.Proyecto_Vet.repository.CategoriaRepository;
import com.example.Proyecto_Vet.repository.ClienteRepository;
import com.example.Proyecto_Vet.repository.EmpleadoRepository;
import com.example.Proyecto_Vet.repository.ProductoRepository;
import com.example.Proyecto_Vet.repository.ProveedorRepository;
import com.example.Proyecto_Vet.repository.ProveedorProductoRepository;
import com.example.Proyecto_Vet.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeeder {

    private final CategoriaRepository categoriaRepo;
    private final ProductoRepository productoRepo;
    private final ProveedorRepository proveedorRepo;
    private final ProveedorProductoRepository proveedorProductoRepo;
    private final ClienteRepository clienteRepo;
    private final EmpleadoRepository empleadoRepo;
    private final VentaRepository ventaRepo;

    @EventListener(ApplicationReadyEvent.class)
    public void seed() {
        if (categoriaRepo.count() > 0) return;

        Categoria med = categoriaRepo.save(new Categoria(null, "Medicamentos"));
        Categoria ali = categoriaRepo.save(new Categoria(null, "Alimentos"));
        Categoria acc = categoriaRepo.save(new Categoria(null, "Accesorios"));
        Categoria vac = categoriaRepo.save(new Categoria(null, "Vacunas"));

        Producto p1 = productoRepo.save(new Producto(null, "Vacuna Antirrabica",       new BigDecimal("35000.00"), 50, vac));
        Producto p2 = productoRepo.save(new Producto(null, "Desparasitante Oral",      new BigDecimal("15000.00"), 80, med));
        Producto p3 = productoRepo.save(new Producto(null, "Concentrado Premium",      new BigDecimal("45000.00"), 60, ali));
        Producto p4 = productoRepo.save(new Producto(null, "Collar Antipulgas",        new BigDecimal("22000.00"), 40, acc));
        Producto p5 = productoRepo.save(new Producto(null, "Antibiotico Amoxicilina",  new BigDecimal("28000.00"), 35, med));
        Producto p6 = productoRepo.save(new Producto(null, "Arena para Gatos",         new BigDecimal("18000.00"), 90, acc));

        Proveedor prv1 = proveedorRepo.save(new Proveedor(null, "Laboratorios VetFarma", "3001234567", "ventas@vetfarma.com"));
        Proveedor prv2 = proveedorRepo.save(new Proveedor(null, "Distribuidora PetLife", "3109876543", "info@petlife.co"));
        Proveedor prv3 = proveedorRepo.save(new Proveedor(null, "Nutricion Animal S.A.", "3205551234", "pedidos@nutrianimal.com"));

        proveedorProductoRepo.save(new ProveedorProducto(null, prv1, p1));
        proveedorProductoRepo.save(new ProveedorProducto(null, prv1, p2));
        proveedorProductoRepo.save(new ProveedorProducto(null, prv1, p5));
        proveedorProductoRepo.save(new ProveedorProducto(null, prv2, p3));
        proveedorProductoRepo.save(new ProveedorProducto(null, prv2, p4));
        proveedorProductoRepo.save(new ProveedorProducto(null, prv2, p6));
        proveedorProductoRepo.save(new ProveedorProducto(null, prv3, p3));
        proveedorProductoRepo.save(new ProveedorProducto(null, prv3, p6));

        Cliente c1 = clienteRepo.save(new Cliente(null, "Juan Perez",     "3145678901", "juan@gmail.com"));
        Cliente c2 = clienteRepo.save(new Cliente(null, "Maria Lopez",    "3208765432", "maria@outlook.com"));
        Cliente c3 = clienteRepo.save(new Cliente(null, "Carlos Gomez",   "3112345678", "carlos@gmail.com"));
        Cliente c4 = clienteRepo.save(new Cliente(null, "Ana Rodriguez",  "3189876543", "ana@hotmail.com"));

        Empleado e1 = empleadoRepo.save(new Empleado(null, "Dra. Laura Gomez", "Veterinaria",     null, null, null));
        Empleado e2 = empleadoRepo.save(new Empleado(null, "Pedro Martinez",   "Auxiliar",        null, null, null));
        Empleado e3 = empleadoRepo.save(new Empleado(null, "Sandra Torres",    "Recepcionista",   null, null, null));

        Venta v1 = ventaRepo.save(new Venta(null, LocalDate.of(2026, 3, 10), c1, e1, new ArrayList<>()));
        Venta v2 = ventaRepo.save(new Venta(null, LocalDate.of(2026, 3, 11), c2, e1, new ArrayList<>()));
        Venta v3 = ventaRepo.save(new Venta(null, LocalDate.of(2026, 3, 12), c3, e2, new ArrayList<>()));
        Venta v4 = ventaRepo.save(new Venta(null, LocalDate.of(2026, 3, 13), c4, e3, new ArrayList<>()));
        Venta v5 = ventaRepo.save(new Venta(null, LocalDate.of(2026, 3, 14), c1, e1, new ArrayList<>()));

        v1.getDetalles().add(new DetalleVenta(null, 2, new BigDecimal("35000.00"), v1, p1));
        v1.getDetalles().add(new DetalleVenta(null, 1, new BigDecimal("15000.00"), v1, p2));
        v2.getDetalles().add(new DetalleVenta(null, 1, new BigDecimal("45000.00"), v2, p3));
        v2.getDetalles().add(new DetalleVenta(null, 2, new BigDecimal("22000.00"), v2, p4));
        v3.getDetalles().add(new DetalleVenta(null, 3, new BigDecimal("15000.00"), v3, p2));
        v3.getDetalles().add(new DetalleVenta(null, 1, new BigDecimal("28000.00"), v3, p5));
        v4.getDetalles().add(new DetalleVenta(null, 2, new BigDecimal("18000.00"), v4, p6));
        v5.getDetalles().add(new DetalleVenta(null, 1, new BigDecimal("35000.00"), v5, p1));
        v5.getDetalles().add(new DetalleVenta(null, 2, new BigDecimal("45000.00"), v5, p3));

        ventaRepo.saveAll(List.of(v1, v2, v3, v4, v5));

        System.out.println("  Datos de prueba insertados correctamente.");
    }
}
