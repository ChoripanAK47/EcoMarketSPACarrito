package com.EcoMarketSPA.Carrito.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CarritoItem")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;
    @Column(unique = true, nullable = false)
    private Integer idCarrito;
}
