# Sistema de Gestión de Pedidos — API REST

API REST desarrollada con **Java + Spring Boot + MySQL** para la gestión de usuarios y pedidos comerciales. Este proyecto demuestra la aplicación de la cultura **Docs as Code**, incluyendo documentación completa del sistema generada a partir de un código "mudo" (sin comentarios ni guías).

---

## 🧰 Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|------------|---------|-----|
| Java | 17 | Lenguaje principal |
| Spring Boot | 3.2.x | Framework backend |
| Spring Data JPA | 3.2.x | Persistencia de datos |
| MySQL | 8.x (XAMPP) | Motor de base de datos |
| Maven | 3.9.x | Gestor de dependencias |
| IntelliJ IDEA | 2023.x+ | IDE de desarrollo |

---

## 📋 Prerrequisitos

Antes de ejecutar este proyecto, asegúrate de tener instalado:

- **JDK 17 o superior** — [Descargar](https://adoptium.net/)
- **XAMPP** (con Apache y MySQL) — [Descargar](https://www.apachefriends.org/)
- **IntelliJ IDEA** (Community o Ultimate) — [Descargar](https://www.jetbrains.com/idea/download/)
- **Maven** (incluido en IntelliJ)

---

## 🗄️ Configuración de la Base de Datos

1. Inicia **XAMPP** y activa los módulos **Apache** y **MySQL**.
2. Abre tu navegador y accede a `http://localhost/phpmyadmin`.
3. Crea una base de datos llamada `sistema_pedidos` con cotejamiento `utf8mb4_general_ci`.
4. Selecciona la base de datos y ve a la pestaña **SQL**.
5. Ejecuta el script ubicado en `docs/schema.sql` (también puedes copiarlo y pegarlo directamente).

---

## ⚙️ Configuración del Proyecto

### Variables de Entorno

Este proyecto se conecta a MySQL local usando la configuración por defecto de XAMPP. Si necesitas cambiar algún parámetro, edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_pedidos?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
```

Crea un archivo `.env.example` en la raíz (opcional, para referencia):

```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=sistema_pedidos
DB_USER=root
DB_PASSWORD=
SERVER_PORT=8080
```

### Instalación

1. Clona el repositorio o descarga los archivos.
2. Abre el proyecto en **IntelliJ IDEA**.
3. Espera a que Maven descargue las dependencias (verás una barra de progreso).
4. Si es necesario, haz clic en **"Reload All Maven Projects"** en el panel de Maven.

---

## ▶️ Ejecución

1. Asegúrate de que MySQL esté corriendo en XAMPP.
2. En IntelliJ, busca la clase `SistemaPedidosApplication.java`.
3. Clic derecho → **Run 'SistemaPedidosApplication'**.
4. La consola mostrará:
   ```
   Tomcat started on port(s): 8080 (http)
   Started SistemaPedidosApplication in X.XXX seconds
   ```

El servidor estará disponible en `http://localhost:8080`.

---

## 📡 Endpoints Principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/usuarios` | Lista todos los usuarios registrados |
| `GET` | `/api/usuarios/{id}` | Obtiene un usuario por su ID |
| `POST` | `/api/usuarios` | Crea un nuevo usuario |
| `GET` | `/api/pedidos` | Lista todos los pedidos |
| `POST` | `/api/pedidos` | Crea un nuevo pedido |

Para más detalles, consulta [`docs/02_manual_tecnico.md`](docs/02_manual_tecnico.md).

---

## 📚 Documentación Adicional

- [Especificación de Requisitos (SRS Ligera)](docs/01_srs_requisitos.md)
- [Manual Técnico](docs/02_manual_tecnico.md)
- [Manual de Usuario](docs/03_manual_usuario.md)

---

## 📁 Estructura del Proyecto

```
sistema-pedidos/
├── README.md
├── pom.xml
├── docs/
│   ├── schema.sql
│   ├── 01_srs_requisitos.md
│   ├── 02_manual_tecnico.md
│   └── 03_manual_usuario.md
├── presentacion/
│   └── presentacion_defensa.pptx
└── src/main/
    ├── java/com/recuperacion/sistemapedidos/
    │   ├── SistemaPedidosApplication.java
    │   ├── controller/
    │   ├── dto/
    │   ├── entity/
    │   └── repository/
    └── resources/
        └── application.properties
```

---

## 👤 Autor

**[Tu Nombre Completo]**
Proyecto de recuperación — Módulo de Documentación de Software