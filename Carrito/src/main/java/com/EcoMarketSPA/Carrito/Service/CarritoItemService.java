package com.EcoMarketSPA.CarritoItem.Service;

import com.EcoMarketSPA.CarritoItem.Model.CarritoItem;
import com.EcoMarketSPA.CarritoItem.Repository.CarritoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarritoItemService {

    @Autowired
    private CarritoItemRepository carritoItemRepository;

    // Obtener todos los items
    public List<CarritoItem> findAll() {
        return carritoItemRepository.findAll();
    }

    // Buscar un item por su ID
    public CarritoItem findById(Integer id) {
        return carritoItemRepository.findById(id).orElse(null);
    }

    // Guardar un item
    public CarritoItem save(CarritoItem carritoItem) {
        return carritoItemRepository.save(carritoItem);
    }

    // Eliminar un item por su ID
    public void delete(Integer id) {
        carritoItemRepository.deleteById(id);
    }
}
