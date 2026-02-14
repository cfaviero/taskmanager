# 🗂 Task Manager API

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0-green)
![Maven](https://img.shields.io/badge/build-Maven-red)
![Database](https://img.shields.io/badge/Database-MySQL-orange)

API REST para gestión de tareas desarrollada con Spring Boot.
Permite crear, actualizar, eliminar y filtrar tareas por estado y prioridad.

---

## 📌 Características

- CRUD completo de tareas
- Filtros por estado (`TaskStatus`)
- Filtros por prioridad (`TaskPriority`)
- Validación con Bean Validation
- Manejo global de excepciones
- Respuestas de error estructuradas
- Uso de DTOs (separación dominio / exposición API)
- Logging con SLF4J
- Transacciones con `@Transactional`
- Persistencia con JPA + MySQL

---

## 🏗 Arquitectura

Arquitectura en capas:

```
Controller → Service → Repository → Database
```

Separación clara de responsabilidades:

- `controller` → Exposición REST
- `service` → Lógica de negocio
- `repository` → Acceso a datos
- `dto` → Objetos de transferencia
- `exception` → Manejo centralizado de errores
- `model` → Entidades y enums

---

## 🛠 Tecnologías

- Java 21
- Spring Boot 4.0.2
- Spring Data JPA
- Hibernate
- MySQL
- Jakarta Validation
- Lombok
- Maven

---

## ⚙ Requisitos

- Java 21+
- Maven 3.9+
- MySQL 8+

---

## 🗄 Configuración Base de Datos

Crear base de datos:

```sql
CREATE DATABASE taskmanager;
```

Configurar `application.yml` o `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🚀 Ejecutar Proyecto

```bash
mvn clean install
mvn spring-boot:run
```

La API estará disponible en:

```
http://localhost:8080/api/tasks
```

---

## 📡 Endpoints

### Crear tarea

```
POST /api/tasks
```

Body:

```json
{
  "title": "Estudiar Spring",
  "description": "Practicar arquitectura en capas",
  "priority": "HIGH",
  "dueDate": "2026-03-01T18:00:00"
}
```

---

### Obtener todas las tareas

```
GET /api/tasks
```

Filtrar por estado:

```
GET /api/tasks?status=PENDING
```

Filtrar por prioridad:

```
GET /api/tasks?priority=HIGH
```

---

### Obtener por ID

```
GET /api/tasks/{id}
```

---

### Actualizar tarea

```
PUT /api/tasks/{id}
```

---

### Eliminar tarea

```
DELETE /api/tasks/{id}
```

---

## ❌ Manejo de Errores

### 404 – Recurso no encontrado

```json
{
  "timestamp": "2026-02-14T10:15:00",
  "status": 404,
  "error": "Not Found",
  "message": "Tarea con id 10 no encontrada",
  "path": "/api/tasks/10"
}
```

---

### 400 – Error de validación

```json
{
  "timestamp": "2026-02-14T10:20:00",
  "status": 400,
  "error": "Validation Failed",
  "errors": {
    "title": "El titulo es obligatorio"
  },
  "path": "/api/tasks"
}
```

---

## 📌 Modelo de Datos

Entidad `Task`:

- id
- title
- description
- status (PENDING, IN_PROGRESS, COMPLETED)
- priority (LOW, MEDIUM, HIGH)
- dueDate
- createdAt
- updatedAt

Auditoría automática con `@PrePersist` y `@PreUpdate`.

---

## 🧠 Decisiones Técnicas

- Uso de DTO para evitar exponer entidad directamente
- Manejo global de excepciones con `@RestControllerAdvice`
- Enums persistidos como `STRING` (evita problemas al reordenar valores)
- Transacciones declarativas con `@Transactional`
- Validaciones a nivel de entrada con anotaciones

---

## 📄 Licencia

Proyecto de práctica backend.
