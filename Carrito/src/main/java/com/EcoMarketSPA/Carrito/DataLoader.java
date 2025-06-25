package com.EcoMarketSPA.Carrito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.EcoMarketSPA.Carrito.Model.Carrito;
import com.EcoMarketSPA.Carrito.Model.CarritoItem;
import com.EcoMarketSPA.Carrito.Repository.CarritoItemRepository;
import com.EcoMarketSPA.Carrito.Repository.CarritoRepository;

import net.datafaker.Faker;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private CarritoItemRepository carritoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar 30 IDs de usuario únicos entre 1 y 100
        List<Integer> usuarioIds = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            usuarioIds.add(i);
        }
        Collections.shuffle(usuarioIds);

        // Generar 10 carritos con usuario_id único
        List<Carrito> carritos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Carrito carrito = new Carrito();
            carrito.setUsuarioId(usuarioIds.get(i));
            carrito.setTotal(Double.parseDouble(faker.commerce().price(1000.0, 50000.0)));
            carrito.setEstado(faker.options().option("Pendiente", "Completado", "Cancelado"));
            carritoRepository.save(carrito);
            carritos.add(carrito);
        }

        // Crear una lista para repartir los 20 items
        int totalItems = 20;
        int carritosRestantes = carritos.size();
        int itemsRestantes = totalItems;

        for (Carrito carrito : carritos) {
            // Asegura al menos 1 item por carrito, el resto se reparte aleatoriamente
            int maxItems = itemsRestantes - (carritosRestantes - 1); // deja al menos 1 para cada carrito restante
            int cantidadItems = 1;
            if (maxItems > 1) {
                cantidadItems += random.nextInt(maxItems); // suma aleatorio entre 0 y maxItems-1
            }
            for (int j = 0; j < cantidadItems; j++) {
                CarritoItem carritoItem = new CarritoItem();
                carritoItem.setCantidad(random.nextInt(5) + 1);
                carritoItem.setPrecio(Double.parseDouble(faker.commerce().price(1000.0, 50000.0)));
                carritoItem.setNombre(faker.commerce().productName());
                carritoItem.setDescripcion(faker.lorem().sentence(10));
                carritoItem.setCarrito(carrito);
                carritoItemRepository.save(carritoItem);
            }
            itemsRestantes -= cantidadItems;
            carritosRestantes--;
        }
    }
}
