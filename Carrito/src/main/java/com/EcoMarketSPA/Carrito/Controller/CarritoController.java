package com.EcoMarketSPA.Carrito.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EcoMarketSPA.Carrito.Model.Carrito;
import com.EcoMarketSPA.Carrito.Service.CarritoService;

@RestController
@RequestMapping("/api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;
    
    @GetMapping
    public ResponseEntity<List<Carrito>> listarCarritos() {
        List<Carrito> carritos = carritoService.findAll();
        if (carritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carritos);
    }
    @PostMapping
    public ResponseEntity<Carrito> crearCarrito(@RequestBody Carrito carrito) {
        Carrito nuevoCarrito = carritoService.save(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarrito);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Carrito> buscarId(@PathVariable Integer idCarrito) {
        try {
            Carrito carrito = carritoService.findById(idCarrito);
            if (carrito == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable Integer idCarrito, Carrito carrito) {
        try {
            Carrito carritoExistente = carritoService.findById(idCarrito);
            if (carritoExistente == null) {
                return ResponseEntity.notFound().build();
            }
            carrito.setId(idCarrito);
            Carrito carritoActualizado = carritoService.save(carrito);
            return ResponseEntity.ok(carritoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Integer idCarrito) {
        try {
            Carrito carrito = carritoService.findById(idCarrito);
            if (carrito == null) {
                return ResponseEntity.notFound().build();
            }
            carritoService.delete(idCarrito);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
