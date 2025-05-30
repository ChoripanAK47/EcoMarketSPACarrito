package com.EcoMarketSPA.Carrito.Controller;

import com.EcoMarketSPA.Carrito.Model.CarritoItem;
import com.EcoMarketSPA.Carrito.Service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrito-items")
public class CarritoItemController {

    @Autowired
    private CarritoService carritoItemService;

    // Listar todos los items
    @GetMapping
    public ResponseEntity<List<CarritoItem>> listar() {
        List<CarritoItem> items = carritoItemService.findAll();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

    // Buscar un item por su ID
    @GetMapping("/{id}")
    public ResponseEntity<CarritoItem> buscar(@PathVariable Integer id) {
        CarritoItem item = carritoItemService.findById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    // Agregar un nuevo item
    @PostMapping
    public ResponseEntity<CarritoItem> agregar(@RequestBody CarritoItem carritoItem) {
        CarritoItem nuevo = carritoItemService.save(carritoItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Actualizar un item existente
    @PutMapping("/{id}")
    public ResponseEntity<CarritoItem> actualizar(@PathVariable Integer id, @RequestBody CarritoItem carritoItem) {
        CarritoItem existente = carritoItemService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setProductoId(carritoItem.getProductoId());
        existente.setCantidad(carritoItem.getCantidad());
        existente.setPrecioUnitario(carritoItem.getPrecioUnitario());
        // El subtotal se calcula dinámicamente, no es necesario actualizarlo manualmente
        CarritoItem actualizado = carritoItemService.save(existente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un item por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        CarritoItem existente = carritoItemService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        carritoItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
