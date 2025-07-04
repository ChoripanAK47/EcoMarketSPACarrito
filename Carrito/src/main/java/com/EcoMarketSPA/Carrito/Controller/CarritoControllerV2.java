package com.EcoMarketSPA.Carrito.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
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
import com.EcoMarketSPA.Carrito.Model.CarritoItem;
import com.EcoMarketSPA.Carrito.Service.CarritoService;
import com.EcoMarketSPA.Carrito.assemblers.CarritoItemModelAssembler;
import com.EcoMarketSPA.Carrito.assemblers.CarritoModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/carrito")
@Tag(name = "Carrito", description = "Operaciones relacionadas con el carrito de compras")
public class CarritoControllerV2 {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CarritoModelAssembler assembler;
    @Autowired
    private CarritoItemModelAssembler itemAssembler;
    
    // Metodo para obtener los items del carrito por ID de usuario
    @GetMapping(value = "/carrito/idusuario/{usuarioId}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar carrito por ID de usuario", description = "Obtiene el carrito de compras asociado a un usuario específico")
    public ResponseEntity<CollectionModel<EntityModel<CarritoItem>>> obtenerItemsPorUsuario(@PathVariable Integer usuarioId) {
        Carrito carrito = carritoService.findByUsuarioId(usuarioId);
        if (carrito == null || carrito.getItems() == null || carrito.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EntityModel<CarritoItem>> itemsModel = carrito.getItems().stream()
            .map(itemAssembler::toModel)
            .toList();
        CollectionModel<EntityModel<CarritoItem>> collectionModel = CollectionModel.of(itemsModel);
        return ResponseEntity.ok(collectionModel);
    }
    @Operation(summary = "Obtener todos los carritos", description = "Muestra todos los carritos de compras")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Carrito>> getAllCarritos() {
        List<EntityModel<Carrito>> carritos = carritoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(carritos,
                linkTo(methodOn(CarritoControllerV2.class).getAllCarritos()).withSelfRel());
    }

    @Operation(summary = "Buscar carrito por ID", description = "Obtiene el carrito de compras asociado a un ID específico")
    @GetMapping(value = "/{idCarrito}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Carrito> getCarritoById(@PathVariable Integer idCarrito) {
        Carrito carrito = carritoService.findById(idCarrito);
        return assembler.toModel(carrito);
    }

    @GetMapping
    @Operation(summary = "Listar todos los carritos", description = "Obtiene una lista de todos los carritos de compras")
    public ResponseEntity<List<Carrito>> listarCarritos() {
        List<Carrito> carritos = carritoService.findAll();
        if (carritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carritos);
    }
    @Operation(summary = "Operación para crear un carrito", description = "Crea un nuevo carrito de compras")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrito>> createCarrito(@RequestBody Carrito carrito) {
        Carrito newCarrito = carritoService.save(carrito);
        return ResponseEntity
                .created(linkTo(methodOn(CarritoControllerV2.class).getCarritoById(newCarrito.getIdCarrito())).toUri())
                .body(assembler.toModel(newCarrito));
    }
    @Operation(summary = "Operación para actualizar un carrito", description = "Actualiza un carrito de compras existente")
    @PutMapping(value = "/{idCarrito}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrito>> updateCarrito(@PathVariable Integer idCarrito, @RequestBody Carrito carrito) {
        carrito.setIdCarrito(idCarrito);
        Carrito updatedCarrito = carritoService.save(carrito);
        return ResponseEntity
                .ok(assembler.toModel(updatedCarrito));
    }
    @Operation(summary = "Operación para eliminar un carrito", description = "Elimina un carrito de compras existente")
    @DeleteMapping(value = "/{idCarrito}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCarrito(@PathVariable Integer idCarrito) {
        carritoService.delete(idCarrito);
        return ResponseEntity.noContent().build();
    }
}
