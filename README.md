# Veterinaria - Sistema de Gestion

Aplicacion web para la gestion de una veterinaria desarrollada con **Spring Boot 4**, **Thymeleaf** y **H2 Database**.

## Tecnologias usadas

- Java 21+
- Spring Boot 4.0.3
- Spring Security (autenticacion con formulario y JWT)
- Spring Data JPA
- Thymeleaf (vistas del lado del servidor)
- H2 Database (base de datos embebida, sin necesidad de instalacion)
- Lombok
- Bootstrap 5 (interfaz de usuario)

## Requisitos

- JDK 21 o superior
- Maven 3.9+
- Visual Studio Code, IntelliJ IDEA o Eclipse
- Git (opcional)

## Como ejecutar localmente

### 1. Clonar el repositorio

```bash
git clone https://github.com/M1GU3L-0L/Proyecto-Final---Spring-Boot---Thymeleaf.git
cd Proyecto-Final---Spring-Boot---Thymeleaf
```

### 2. Ejecutar con Maven

```bash
mvn clean spring-boot:run
```

La primera vez descargara las dependencias automaticamente.

### 3. Acceder a la aplicacion

Abri el navegador en: [http://localhost:8080](http://localhost:8080)

### 4. Crear una cuenta

1. Hace clic en **"Registrate aqui"** en la pantalla de login
2. Completa el formulario con nombre, usuario y contrasena
3. Inicia sesion con las credenciales creadas

## Funcionalidades

- **Productos**: CRUD de productos con categorias
- **Categorias**: Administracion de categorias
- **Clientes**: Registro y gestion de clientes
- **Proveedores**: Administracion de proveedores
- **Empleados**: Gestion de empleados y roles
- **Ventas**: Registro de ventas con detalle de productos

## Base de datos

El proyecto usa **H2** en modo embebido (archivo). No requiere instalar PostgreSQL ni ningun otro motor de base de datos.

- Los datos se guardan en la carpeta `data/` del proyecto
- Consola H2 disponible en: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - JDBC URL: `jdbc:h2:file:./data/veterinaria`
  - Usuario: `sa`
  - Contrasena: (vacia)

La primera vez que se ejecuta, la aplicacion crea las tablas automaticamente y carga datos de prueba (categorias, productos, clientes, proveedores, etc.).

## Estructura del proyecto

```
src/
  main/
    java/com/example/Proyecto_Vet/
      controller/       # Controladores REST (API)
        web/            # Controladores web (Thymeleaf)
      model/            # Entidades JPA
      repository/       # Repositorios Spring Data
      security/         # Configuracion de Spring Security y JWT
      service/          # Logica de negocio
    resources/
      templates/        # Plantillas Thymeleaf
      application.properties  # Configuracion de la aplicacion
pom.xml                 # Dependencias y build
```

## Notas

- Los logs de SQL se muestran en consola (se pueden desactivar en `application.properties`)
- El puerto por defecto es `8080`
