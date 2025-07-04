package com.EcoMarketSPA.Carrito;

import org.junit.jupiter.api.Test;

import com.EcoMarketSPA.Carrito.Model.Carrito;

import static org.junit.jupiter.api.Assertions.*;

public class CarritoTest {

    @Test
    void testCarritoCreacion() {
        Carrito carrito = new Carrito();
        assertNotNull(carrito);
    }

}
