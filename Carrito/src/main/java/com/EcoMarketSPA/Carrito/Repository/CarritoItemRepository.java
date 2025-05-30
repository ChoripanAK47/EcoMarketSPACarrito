package com.EcoMarketSPA.CarritoItem.Repository;

import com.EcoMarketSPA.CarritoItem.Model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {
    
}
