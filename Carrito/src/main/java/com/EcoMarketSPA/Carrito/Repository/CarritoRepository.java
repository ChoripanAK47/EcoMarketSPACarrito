package com.EcoMarketSPA.Carrito.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EcoMarketSPA.Carrito.Model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer>{
    Carrito findByUsuarioId(int usuarioId);
    Carrito findByEstado(String estado);
    
}
