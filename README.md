# Car Management API

Este proyecto es una API REST desarrollada con Spring Boot para la gestión de vehículos, que incluye autenticación JWT y operaciones CRUD.

## Requisitos Previos

- Java JDK 17 o superior
- SQL Server
- Maven (opcional, se puede usar el wrapper incluido)
- Un cliente REST como Postman para probar los endpoints

## Configuración de la Base de Datos

1. Asegúrate de tener SQL Server instalado y corriendo
2. La aplicación está configurada para conectarse a:
   - Servidor: `PC\SQLEXPRESS`
   - Usuario: `sa`
   - Contraseña: `4dm1n1str4t0r`

## Configuración del Proyecto

1. Clona el repositorio:
   ```bash
   git clone <url-del-repositorio>
   cd carmanagement
   ```

2. Configura la base de datos en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:sqlserver://PC\\SQLEXPRESS:1433;databaseName=carmanagement;encrypt=true;trustServerCertificate=true
   spring.datasource.username=sa
   spring.datasource.password=4dm1n1str4t0r
   ```

## Ejecutar el Proyecto

1. Usando Maven Wrapper (recomendado):
   ```bash
   ./mvnw spring-boot:run   # En Linux/Mac
   .\mvnw.cmd spring-boot:run  # En Windows
   ```

2. Usando Maven (si está instalado):
   ```bash
   mvn spring-boot:run
   ```

La aplicación estará disponible en: `http://localhost:8080`

## Endpoints Disponibles

### Autenticación

#### Registro de Usuario
- **URL**: `POST /api/auth/register`
- **Body**:
  ```json
  {
      "userName": "usuario1",
      "email": "usuario1@ejemplo.com",
      "password": "tu_contraseña",
      "userAddress": "Tu dirección"
  }
  ```

#### Login
- **URL**: `POST /api/auth/login`
- **Body**:
  ```json
  {
      "email": "usuario1@ejemplo.com",
      "password": "tu_contraseña"
  }
  ```

### Gestión de Coches

Para todos los endpoints de coches, se requiere el token JWT en el header:
```
Authorization: Bearer tu_token_jwt
```

#### Crear Coche
- **URL**: `POST /api/cars`
- **Body**:
  ```json
  {
      "brand": "Toyota",
      "model": "Corolla",
      "carYear": 2023,
      "licensePlate": "ABC123",
      "color": "Red"
  }
  ```

#### Obtener Todos los Coches del Usuario
- **URL**: `GET /api/cars`

#### Obtener un Coche Específico
- **URL**: `GET /api/cars/{id}`
- **Ejemplo**: `GET /api/cars/11`

#### Actualizar un Coche
- **URL**: `PUT /api/cars/{id}`
- **Body**:
  ```json
  {
      "brand": "Toyota",
      "model": "Corolla",
      "carYear": 2023,
      "licensePlate": "ABC123",
      "color": "Blue"
  }
  ```

#### Eliminar un Coche
- **URL**: `DELETE /api/cars/{id}`
- **Ejemplo**: `DELETE /api/cars/11`

## Validaciones

- El año del coche (`carYear`) debe estar entre 1920 y 2024
- La placa del coche (`licensePlate`) debe ser única
- Todos los campos son obligatorios
- El email debe ser único en el sistema
- Solo puedes gestionar los coches que te pertenecen

## Respuestas de Error

La API devuelve los siguientes códigos de estado HTTP:
- 200: Operación exitosa
- 201: Recurso creado exitosamente
- 400: Error de validación
- 401: No autenticado
- 403: No autorizado
- 404: Recurso no encontrado
- 500: Error interno del servidor

## Seguridad

- La API utiliza JWT (JSON Web Tokens) para la autenticación
- Las contraseñas se almacenan hasheadas en la base de datos
- Cada usuario solo puede acceder y modificar sus propios coches
- Los tokens JWT expiran después de un tiempo determinado

## Soporte

Si encuentras algún problema o tienes alguna pregunta, por favor abre un issue en el repositorio. 