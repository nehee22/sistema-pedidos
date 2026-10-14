# Manual Técnico — Diccionario de Datos y API

**Proyecto:** Sistema de Gestión de Pedidos
**Versión:** 1.0
**Base de datos:** MySQL 8.x (XAMPP)
**Framework:** Spring Boot 3.2.x

---

## 1. Arquitectura del Sistema

El sistema sigue una arquitectura en capas típica de Spring Boot:

```
┌──────────────────┐
│   Cliente        │  (Postman / Navegador)
│   (HTTP/REST)    │
└────────┬─────────┘
         │ JSON
         ▼
┌──────────────────┐
│  Controller      │  (UsuarioController, PedidoController)
└────────┬─────────┘
         │
         ▼
┌──────────────────┐
│  Repository      │  (UsuarioRepository, PedidoRepository)
│  (Spring Data)   │
└────────┬─────────┘
         │ JPA/Hibernate
         ▼
┌──────────────────┐
│  MySQL           │  (Base de datos: sistema_pedidos)
│  (XAMPP)         │
└──────────────────┘
```

---

## 2. Diccionario de Datos

### 📋 Tabla: `usuarios`

Almacena la información de los usuarios registrados en el sistema.

| Campo | Tipo | Longitud | Descripción | Restricciones |
|-------|------|----------|-------------|---------------|
| `id` | BIGINT | — | Identificador único del usuario | **PK**, AUTO_INCREMENT, NOT NULL |
| `nombre` | VARCHAR | 100 | Nombre completo del usuario | NOT NULL |
| `email` | VARCHAR | 150 | Correo electrónico del usuario | NOT NULL, UNIQUE |
| `fecha_registro` | TIMESTAMP | — | Fecha y hora de registro | DEFAULT CURRENT_TIMESTAMP |

**Ejemplo de registro:**
```
id: 1
nombre: "Ana Torres"
email: "ana.torres@example.com"
fecha_registro: "2026-10-13 12:12:38"
```

---

### 📋 Tabla: `pedidos`

Almacena los pedidos realizados por los usuarios.

| Campo | Tipo | Longitud | Descripción | Restricciones |
|-------|------|----------|-------------|---------------|
| `id` | BIGINT | — | Identificador único del pedido | **PK**, AUTO_INCREMENT, NOT NULL |
| `usuario_id` | BIGINT | — | ID del usuario que realiza el pedido | **FK** → usuarios.id, NOT NULL |
| `producto` | VARCHAR | 150 | Nombre del producto solicitado | NOT NULL |
| `cantidad` | INT | — | Cantidad solicitada del producto | NOT NULL |
| `estado` | VARCHAR | 50 | Estado del pedido | DEFAULT 'pendiente' |
| `fecha_pedido` | TIMESTAMP | — | Fecha y hora del pedido | DEFAULT CURRENT_TIMESTAMP |

**Ejemplo de registro:**
```
id: 1
usuario_id: 1
producto: "Laptop HP"
cantidad: 1
estado: "pendiente"
fecha_pedido: "2026-10-13 18:12:40"
```

---

### 🔗 Relaciones

- **usuarios (1) ──── (N) pedidos**: Un usuario puede tener múltiples pedidos. La relación se implementa con una clave foránea `usuario_id` en la tabla `pedidos`, con `ON DELETE CASCADE`.

---

## 3. Documentación de Endpoints de la API

### 🌐 Endpoint 1: Obtener todos los usuarios

| Atributo | Valor |
|----------|-------|
| **Método** | `GET` |
| **Ruta** | `/api/usuarios` |
| **Descripción** | Devuelve la lista completa de usuarios registrados. |
| **Autenticación** | No requerida |

**Payload de entrada:** Ninguno.

**Respuesta 200 (OK):**
```json
[
  {
    "id": 1,
    "nombre": "Ana Torres",
    "email": "ana.torres@example.com",
    "fechaRegistro": "2026-10-13T12:12:38"
  },
  {
    "id": 2,
    "nombre": "Luis Gomez",
    "email": "luis.gomez@example.com",
    "fechaRegistro": "2026-10-13T12:12:38"
  }
]
```

---

### 🌐 Endpoint 2: Obtener un usuario por ID

| Atributo | Valor |
|----------|-------|
| **Método** | `GET` |
| **Ruta** | `/api/usuarios/{id}` |
| **Descripción** | Devuelve los datos de un usuario específico. |
| **Parámetro de ruta** | `id` (Long) — ID del usuario |

**Respuesta 200 (OK):**
```json
{
  "id": 1,
  "nombre": "Ana Torres",
  "email": "ana.torres@example.com",
  "fechaRegistro": "2026-10-13T12:12:38"
}
```

**Respuesta 400 (Bad Request):**
```
"Usuario no encontrado con ID: 99"
```

---

### 🌐 Endpoint 3: Crear un nuevo pedido

| Atributo | Valor |
|----------|-------|
| **Método** | `POST` |
| **Ruta** | `/api/pedidos` |
| **Descripción** | Crea un nuevo pedido asociado a un usuario existente. |
| **Content-Type** | `application/json` |

**Payload de entrada:**
```json
{
  "usuarioId": 1,
  "producto": "Teclado Mecánico",
  "cantidad": 1
}
```

| Campo | Tipo | Obligatorio | Descripción |
|-------|------|-------------|-------------|
| `usuarioId` | Long | Sí | ID del usuario que realiza el pedido |
| `producto` | String | Sí | Nombre del producto |
| `cantidad` | Integer | Sí | Cantidad solicitada (> 0) |

**Respuesta 200 (OK):**
```json
{
  "id": 3,
  "usuario": {
    "id": 1,
    "nombre": "Ana Torres",
    "email": "ana.torres@example.com",
    "fechaRegistro": "2026-10-13T12:12:38"
  },
  "producto": "Teclado Mecánico",
  "cantidad": 1,
  "estado": "pendiente",
  "fechaPedido": "2026-10-13T18:20:00"
}
```

**Respuesta 400 (Bad Request) — Datos faltantes:**
```
"Faltan datos obligatorios: usuarioId, producto, cantidad."
```

**Respuesta 400 (Bad Request) — Usuario inexistente:**
```
"El usuario con ID 999 no existe."
```

**Respuesta 400 (Bad Request) — Cantidad inválida:**
```
"La cantidad debe ser mayor a 0."
```

---

### 🌐 Endpoint 4: Listar todos los pedidos

| Atributo | Valor |
|----------|-------|
| **Método** | `GET` |
| **Ruta** | `/api/pedidos` |
| **Descripción** | Devuelve la lista de todos los pedidos registrados. |

**Respuesta 200 (OK):**
```json
[
  {
    "id": 1,
    "usuario": {
      "id": 1,
      "nombre": "Ana Torres",
      "email": "ana.torres@example.com"
    },
    "producto": "Laptop HP",
    "cantidad": 1,
    "estado": "pendiente",
    "fechaPedido": "2026-10-13T18:12:40"
  }
]
```

---

## 4. Códigos HTTP Utilizados

| Código | Significado | Cuándo se usa |
|--------|-------------|---------------|
| `200` | OK | Solicitud procesada correctamente |
| `400` | Bad Request | Error en los datos enviados por el cliente |
| `404` | Not Found | Recurso no encontrado (reservado para futuras versiones) |
| `500` | Internal Server Error | Error interno del servidor (no controlado) |

---

## 5. Notas Técnicas

- **Serialización JSON:** Se utiliza `@JsonIgnore` en la colección `pedidos` de la entidad `Usuario` para evitar **recursión infinita** al serializar relaciones bidireccionales.
- **CORS:** No configurado. Si se desea consumir desde un frontend externo, agregar configuración de CORS.
- **Validaciones:** Implementadas manualmente en los controladores. Para mayor robustez, se recomienda usar `@Valid` con Bean Validation en futuras versiones.