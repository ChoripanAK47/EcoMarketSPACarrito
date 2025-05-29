# EcoMarketSPA Carrito API

API RESTful para la gestión de carritos de compras de EcoMarketSPA. Permite crear, listar, actualizar y eliminar carritos asociados a usuarios.

## Descripción del Modelo de Datos

- **Usuario** tiene un **Carrito** (1 a 1).
- **Usuario** puede tener muchos **Pedidos** (1 a muchos).
- **Pedido** puede tener muchos **DetallePedido** (1 a muchos).
- **Carrito** puede tener muchos **CarritoItem** (1 a muchos).
- **Producto** puede estar en muchos **CarritoItem** y **DetallePedido** (1 a muchos).

Modelo:

- **Carrito**
  - `id`: Identificador único del carrito.
  - `usuarioId`: Identificador del usuario propietario (único y obligatorio).
  - `total`: Total acumulado en el carrito (obligatorio).
  - `estado`: Estado actual del carrito (obligatorio, por ejemplo: "activo", "cerrado").

## Endpoints

Base URL: `/api/v1/carrito`

| Método | Endpoint      | Descripción                    | Body (JSON)                        |
|--------|---------------|--------------------------------|------------------------------------|
| GET    | `/`           | Listar todos los carritos      | -                                  |
| GET    | `/{id}`       | Obtener carrito por ID         | -                                  |
| GET    | `/usuario/{usuarioId}` | Obtener carrito por usuarioId | -                             |
| GET    | `/estado/{estado}`     | Obtener carrito por estado    | -                             |
| POST   | `/`           | Crear nuevo carrito            | `{ "usuarioId", "total", "estado" }` |
| PUT    | `/{id}`       | Actualizar carrito existente   | `{ "usuarioId", "total", "estado" }` |
| DELETE | `/{id}`       | Eliminar carrito por ID        | -                                  |

### Ejemplo de JSON para POST/PUT

```json
{
  "usuarioId": 1,
  "total": 100.0,
  "estado": "activo"
}
```

## Ejemplo de Respuesta

```json
{
  "id": 1,
  "usuarioId": 1,
  "total": 100.0,
  "estado": "activo"
}
```

## Requisitos

- Java 17+
- Maven
- MySQL (configurado en AWS)

## Configuración

Configura la conexión a MySQL en `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://<host>:<port>/<database>
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña>
spring.jpa.hibernate.ddl-auto=update
```

## Ejecución

1. Clona el repositorio.
2. Configura el archivo `application.properties`.
3. Ejecuta la aplicación: `EcoMarketSpaCarritoApplication`.

La API estará disponible en `http://localhost:8080/api/v1/carrito`.

---

> Proyecto de aprendizaje desarrollado con Spring Boot.