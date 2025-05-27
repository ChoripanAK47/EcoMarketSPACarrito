package com.EcoMarketSPA.Carrito.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EcoMarketSPA.Carrito.Model.Carrito;
import com.EcoMarketSPA.Carrito.Repository.CarritoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    
    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }
    public Carrito findById(Integer id) {
        return carritoRepository.findById(id).orElse(null);
    }
    public Carrito findByUsuarioId(int usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }
    public Carrito findByEstado(String estado) {
        return carritoRepository.findByEstado(estado);
    }
    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }
    public void delete(Integer idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }

}
