package com.EcoMarketSPA.Carrito.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarrito;
    @Column(unique = true, nullable = false, length = 2)
    private Integer usuarioId;
    @Column(nullable = false, length = 6)
    private double total;
    @Column(nullable = false, length = 20)
    private String estado; 
    
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private List<CarritoItem> items = new ArrayList<>();
}
