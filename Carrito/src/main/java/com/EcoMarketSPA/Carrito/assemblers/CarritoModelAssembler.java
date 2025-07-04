package com.EcoMarketSPA.Carrito.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.EcoMarketSPA.Carrito.Controller.CarritoControllerV2;
import com.EcoMarketSPA.Carrito.Model.Carrito;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {

    @Override
    public EntityModel<Carrito> toModel(Carrito carrito) {
        return EntityModel.of(carrito,
                linkTo(methodOn(CarritoControllerV2.class).listarCarritos()).withRel("carritos"),
                linkTo(methodOn(CarritoControllerV2.class).getAllCarritos()).withRel("carritos"));
    }
}
