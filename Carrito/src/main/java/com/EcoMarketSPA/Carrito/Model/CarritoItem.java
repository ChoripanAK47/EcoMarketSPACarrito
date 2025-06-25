package com.EcoMarketSPA.Carrito.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, length = 3)
    private Integer cantidad;

    @Column(nullable = false, length = 6)
    private Double precio;

    // Subtotal calculado dinámicamente
    public Double getSubtotal() {
        return this.cantidad * this.precio;
    }

    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Carrito carrito;
}