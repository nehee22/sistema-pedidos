# Especificación de Requisitos de Software (SRS Ligera)

**Proyecto:** Sistema de Gestión de Pedidos
**Estándar de referencia:** IEEE 830 (adaptado — SRS Ligera)
**Versión:** 1.0
**Autor:** [Tu Nombre Completo]
**Fecha:** [Fecha actual]

---

## 1. Introducción

### 1.1 Propósito
Este documento especifica los requisitos funcionales y no funcionales del **Sistema de Gestión de Pedidos**, una API REST que permite administrar usuarios y registrar pedidos comerciales.

### 1.2 Alcance
El sistema permite:
- Consultar la lista de usuarios registrados.
- Crear nuevos pedidos asociados a usuarios existentes.
- Validar la integridad de los datos (usuario existente, cantidad positiva, campos obligatorios).

### 1.3 Definiciones y Acrónimos
| Término | Significado |
|---------|-------------|
| API | Application Programming Interface |
| REST | Representational State Transfer |
| SRS | Software Requirements Specification |
| RF | Requisito Funcional |
| RNF | Requisito No Funcional |
| BDD | Behavior-Driven Development |
| JPA | Java Persistence API |

---

## 2. Requisitos Funcionales (RF)

| ID | Descripción | Prioridad |
|----|-------------|-----------|
| **RF-01** | El sistema debe permitir consultar la lista completa de usuarios registrados en la base de datos. | Alta |
| **RF-02** | El sistema debe permitir la creación de un nuevo pedido asociado a un usuario existente. | Alta |
| **RF-03** | El sistema debe validar que el `usuarioId` proporcionado exista antes de crear un pedido, devolviendo un error HTTP 400 en caso contrario. | Alta |
| **RF-04** | El sistema debe asignar automáticamente el estado "pendiente" a todo nuevo pedido creado. | Media |
| **RF-05** | El sistema debe registrar la fecha y hora exacta de cada pedido creado. | Media |

---

## 3. Requisitos No Funcionales (RNF)

| ID | Descripción | Categoría |
|----|-------------|-----------|
| **RNF-01** | La API debe responder a las solicitudes en un tiempo menor a 500 ms en condiciones normales de operación. | Rendimiento |
| **RNF-02** | El sistema debe garantizar la persistencia de los datos utilizando MySQL como motor de base de datos relacional. | Persistencia |
| **RNF-03** | El sistema debe validar los datos de entrada y devolver mensajes de error claros para cada caso de fallo. | Usabilidad |
| **RNF-04** | El código debe seguir las convenciones de nomenclatura de Java y la arquitectura en capas de Spring Boot (Controller → Service → Repository). | Mantenibilidad |

---

## 4. Historias de Usuario

### 🟦 Historia de Usuario 1: Consulta de Usuarios

**Como** administrador del sistema,
**Quiero** ver la lista completa de todos los usuarios registrados,
**Para** poder gestionar los pedidos asociados a cada uno.

**Criterios de Aceptación (Gherkin):**

```gherkin
Escenario: Consulta exitosa de usuarios
  Dado que el administrador accede al sistema
  Cuando realiza una petición GET al endpoint /api/usuarios
  Entonces el sistema responde con un código de estado 200
  Y devuelve una lista JSON con todos los usuarios registrados en MySQL

Escenario: Consulta de un usuario específico
  Dado que el administrador conoce el ID de un usuario
  Cuando realiza una petición GET al endpoint /api/usuarios/{id}
  Entonces el sistema responde con un código 200
  Y devuelve los datos del usuario solicitado

Escenario: Usuario no encontrado
  Dado que el administrador proporciona un ID inexistente
  Cuando realiza una petición GET al endpoint /api/usuarios/{id}
  Entonces el sistema responde con un código 400
  Y devuelve un mensaje indicando que el usuario no fue encontrado
```

---

### 🟦 Historia de Usuario 2: Creación de Pedido

**Como** cliente,
**Quiero** registrar un nuevo pedido indicando el producto y la cantidad,
**Para** poder recibir el producto solicitado.

**Criterios de Aceptación (Gherkin):**

```gherkin
Escenario: Creación exitosa de un pedido
  Dado que el cliente proporciona un usuarioId válido, un producto y una cantidad mayor a 0
  Cuando realiza una petición POST al endpoint /api/pedidos con los datos correctos
  Entonces el sistema responde con un código de estado 200
  Y el pedido se guarda en MySQL con estado "pendiente"
  Y se registra la fecha y hora de creación

Escenario: Error por usuario inexistente
  Dado que el cliente proporciona un usuarioId que no existe en la base de datos
  Cuando realiza una petición POST al endpoint /api/pedidos
  Entonces el sistema responde con un código 400
  Y devuelve el mensaje "El usuario con ID [X] no existe."

Escenario: Error por cantidad inválida
  Dado que el cliente proporciona una cantidad igual o menor a 0
  Cuando realiza una petición POST al endpoint /api/pedidos
  Entonces el sistema responde con un código 400
  Y devuelve el mensaje "La cantidad debe ser mayor a 0."

Escenario: Error por datos faltantes
  Dado que el cliente no proporciona uno o más campos obligatorios (usuarioId, producto, cantidad)
  Cuando realiza una petición POST al endpoint /api/pedidos
  Entonces el sistema responde con un código 400
  Y devuelve el mensaje "Faltan datos obligatorios: usuarioId, producto, cantidad."
```

---

## 5. Trazabilidad

| Requisito | Historia de Usuario | Endpoint |
|-----------|---------------------|----------|
| RF-01 | HU-1 | `GET /api/usuarios` |
| RF-02 | HU-2 | `POST /api/pedidos` |
| RF-03 | HU-2 | `POST /api/pedidos` |
| RF-04 | HU-2 | `POST /api/pedidos` |
| RF-05 | HU-2 | `POST /api/pedidos` |