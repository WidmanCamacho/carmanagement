# Car Management System

Este es un sistema de gestión de vehículos desarrollado con Spring Boot para el backend y React para el frontend.

## Requisitos del Sistema

- Java 17 (JDK)
- Maven
- SQL Server Express
- Node.js y npm (para el frontend)

## Configuración del Entorno

### 1. Configuración de Java

1. Instalar Java 17:
   - Descargar OpenJDK 17 desde [Adoptium](https://adoptium.net/temurin/releases/?version=17)
   - Seleccionar la versión para Windows (x64 MSI)
   - Ejecutar el instalador

2. Configurar JAVA_HOME:
   - Abrir Variables de Entorno del Sistema (Windows + R, escribir `sysdm.cpl`)
   - En Variables del Sistema, crear nueva variable:
     - Nombre: `JAVA_HOME`
     - Valor: `C:\Program Files\Eclipse Adoptium\jdk-17.0.10.7-hotspot` (o tu ruta de instalación)
   - En la variable PATH, agregar: `%JAVA_HOME%\bin`

### 2. Configuración de Base de Datos

1. Instalar SQL Server Express
2. Configurar la base de datos en `application.properties`:
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=car_management;encrypt=true;trustServerCertificate=true
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

## Estructura del Proyecto

### Backend (Spring Boot)

- **Controladores**: Manejo de endpoints REST
  - `AuthController`: Gestión de autenticación
  - `CarController`: Gestión de vehículos

- **Servicios**: Lógica de negocio
  - `AuthenticationService`: Servicios de autenticación
  - `CarService`: Servicios de gestión de vehículos
  - `JwtService`: Servicios de JWT
  - `CustomUserDetailsService`: Servicios de usuario

- **Modelos**:
  - `User`: Entidad de usuario
  - `Car`: Entidad de vehículo

- **Seguridad**:
  - JWT Authentication
  - CORS Configuration
  - Spring Security

### Frontend (React)

El frontend se ejecuta en `http://localhost:3000` y se comunica con el backend en `http://localhost:8080`.

## Configuración de CORS

El backend está configurado para aceptar peticiones desde el frontend con las siguientes características:
- Origen permitido: `http://localhost:3000`
- Métodos HTTP: GET, POST, PUT, DELETE, OPTIONS
- Headers permitidos: Authorization, Content-Type, X-Requested-With
- Credenciales: Permitidas
- Tiempo de caché: 3600 segundos

## Ejecución del Proyecto

### Backend
```bash
# Limpiar y compilar
./mvnw clean package

# Ejecutar
./mvnw spring-boot:run
```

El backend estará disponible en `http://localhost:8080`

### API Endpoints

#### Autenticación
- POST `/api/auth/register`: Registro de usuarios
- POST `/api/auth/login`: Inicio de sesión

#### Vehículos
- GET `/api/cars`: Listar vehículos
- POST `/api/cars`: Crear vehículo
- GET `/api/cars/{id}`: Obtener vehículo por ID
- PUT `/api/cars/{id}`: Actualizar vehículo
- DELETE `/api/cars/{id}`: Eliminar vehículo

## Seguridad

- Autenticación basada en JWT
- Contraseñas encriptadas con BCrypt
- Validación de datos en DTOs
- Manejo global de excepciones

## Notas de Versión

### Última Actualización
- Actualización a Java 17
- Configuración de CORS para permitir peticiones desde el frontend
- Mejoras en la seguridad y manejo de autenticación 