# Manual de Usuario — Sistema de Pedidos

**Versión:** 1.0
**Audiencia:** Usuarios finales del sistema (clientes y administradores)

---

## 1. Introducción

Bienvenido al **Sistema de Gestión de Pedidos**. Esta guía te enseñará, paso a paso, cómo consultar usuarios y registrar pedidos de forma sencilla, sin necesidad de conocimientos técnicos avanzados.

---

## 2. Antes de Empezar

Para usar el sistema, necesitas:
- Que el servidor esté encendido (el administrador lo enciende).
- Una herramienta para enviar solicitudes: tu navegador web o Postman.
- Conocer tu `usuarioId` (te explicamos cómo consultarlo más abajo).

---

## 3. Guía Paso a Paso: Consultar Usuarios

### Paso 1: Abre tu navegador
Abre Chrome, Edge o Firefox.

### Paso 2: Accede a la lista de usuarios
Escribe en la barra de direcciones:
```
http://localhost:8080/api/usuarios
```

### Paso 3: Revisa la información
Verás una lista con todos los usuarios registrados. Busca tu correo electrónico y **anota tu `id`** (lo necesitarás para crear pedidos).

**Ejemplo de respuesta:**
```json
[
  {
    "id": 1,
    "nombre": "Ana Torres",
    "email": "ana.torres@example.com"
  }
]
```

---

## 4. Guía Paso a Paso: Registrar un Pedido

### Paso 1: Abre Postman
Si no tienes Postman, descárgalo gratis desde [postman.com](https://www.postman.com/downloads/).

### Paso 2: Configura la solicitud
- **Método:** `POST`
- **URL:** `http://localhost:8080/api/pedidos`
- **Pestaña:** Body → raw → selecciona **JSON**

### Paso 3: Escribe los datos de tu pedido
```json
{
  "usuarioId": 1,
  "producto": "Laptop HP",
  "cantidad": 1
}
```

**Importante:**
- `usuarioId`: tu ID (lo consultaste en el paso anterior).
- `producto`: el nombre del producto que deseas.
- `cantidad`: la cantidad deseada (debe ser mayor a 0).

### Paso 4: Envía la solicitud
Haz clic en **Send**.

### Paso 5: Confirma tu pedido
Si todo está correcto, verás una respuesta como esta:

```json
{
  "id": 3,
  "usuario": {
    "id": 1,
    "nombre": "Ana Torres"
  },
  "producto": "Laptop HP",
  "cantidad": 1,
  "estado": "pendiente",
  "fechaPedido": "2026-10-13T18:20:00"
}
```

¡Tu pedido fue registrado exitosamente! 🎉

---

## 5. Estados de un Pedido

| Estado | Significado |
|--------|-------------|
| `pendiente` | El pedido fue recibido y está en espera de ser procesado. |
| `enviado` | El pedido ya fue despachado al cliente. |

---

## 6. Preguntas Frecuentes (FAQ)

### ❓ 1. ¿Cómo sé cuál es mi `usuarioId`?
Abre en tu navegador `http://localhost:8080/api/usuarios` y busca tu correo electrónico en la lista. El número que aparece al lado de `"id"` es tu identificador.

---

### ❓ 2. ¿Qué significa que mi pedido esté en estado "pendiente"?
Significa que tu pedido fue registrado correctamente y está en espera de ser procesado por el equipo de logística. Una vez enviado, cambiará a "enviado".

---

### ❓ 3. ¿Qué hago si recibo un error al crear el pedido?
Verifica lo siguiente:
- Que hayas enviado **todos los campos**: `usuarioId`, `producto` y `cantidad`.
- Que el `usuarioId` exista realmente (consúltalo con el paso 3).
- Que la `cantidad` sea un número **mayor a 0**.
- Que el formato del cuerpo sea **JSON válido** (con llaves `{}` y comillas dobles `""`).

Si el problema persiste, contacta al administrador del sistema.

---

### ❓ 4. ¿Puedo registrar varios productos en un mismo pedido?
En esta versión, cada pedido corresponde a **un solo producto**. Si necesitas varios productos, crea un pedido por cada uno.

---

### ❓ 5. ¿Puedo cancelar un pedido?
La cancelación no está disponible en esta versión. Contacta al administrador si necesitas cancelar.

---

## 7. Soporte

Para reportar problemas o sugerencias, contacta al administrador del sistema:
📧 **[correo del administrador]**