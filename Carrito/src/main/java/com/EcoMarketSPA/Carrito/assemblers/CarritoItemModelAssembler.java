package com.EcoMarketSPA.Carrito.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.EcoMarketSPA.Carrito.Controller.CarritoControllerV2;
import com.EcoMarketSPA.Carrito.Model.CarritoItem;

@Component
public class CarritoItemModelAssembler implements RepresentationModelAssembler<CarritoItem, EntityModel<CarritoItem>> {

    @Override
    public EntityModel<CarritoItem> toModel(CarritoItem item) {
        return EntityModel.of(item,
            linkTo(methodOn(CarritoControllerV2.class)
                .obtenerItemsPorUsuario(item.getCarrito().getUsuarioId())).withRel("carrito-items"),
            linkTo(methodOn(CarritoControllerV2.class)
                .listarCarritos()).withRel("carritos")
        );
    }
}
