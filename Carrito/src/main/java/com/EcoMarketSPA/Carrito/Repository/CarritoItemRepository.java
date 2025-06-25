package com.EcoMarketSPA.Carrito.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EcoMarketSPA.Carrito.Model.CarritoItem;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {
    CarritoItem findByidProducto(Integer idProducto);
    CarritoItem findByCantidad(Integer cantidad);
    CarritoItem findByPrecio(Double precio);
}
