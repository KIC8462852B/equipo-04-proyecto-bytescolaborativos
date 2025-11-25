# Motor de Recomendaciones - Bytes Colaborativos

Proyecto desarrollado por el Equipo 4 como parte de la iniciativa Bytes Colaborativos. Sistema de motor de recomendaciones construido con Spring Boot y PostgreSQL.

## 📋 Descripción

Este proyecto implementa un motor de recomendaciones utilizando tecnologías modernas de Java y bases de datos relacionales. La aplicación está containerizada usando Docker para facilitar su despliegue y desarrollo.

## 🚀 Tecnologías

- **Backend**: Java con Spring Boot
- **Base de Datos**: PostgreSQL 15
- **Containerización**: Docker & Docker Compose
- **Documentación API**: Swagger/OpenAPI (SpringDoc)
- **Gestión de Dependencias**: Maven

## 🏗️ Estructura del Proyecto

```
equipo-04-proyecto-bytescolaborativos/
├── motorRecomendaciones/          # Aplicación principal
│   ├── src/                       # Código fuente
│   ├── pom.xml                    # Configuración Maven
│   ├── dockerfile                 # Dockerfile de la aplicación
│   ├── docker-compose.yml         # Configuración Docker Compose
│   └── postman/                   # Colecciones Postman para testing
├── docker-compose.yml             # Docker Compose alternativo
├── LICENSE                        # Licencia MIT
└── README.md                      # Este archivo
```

## 🛠️ Instalación y Configuración

### Prerequisitos

- Docker y Docker Compose instalados
- Java 17 o superior (para desarrollo local)
- Maven (incluido en el proyecto con wrapper)

### Ejecución con Docker

1. Clonar el repositorio:
```bash
git clone https://github.com/KIC8462852B/equipo-04-proyecto-bytescolaborativos.git
cd equipo-04-proyecto-bytescolaborativos
```

2. Ejecutar con Docker Compose (opción raíz):
```bash
docker-compose up -d
```

O ejecutar desde el directorio de la aplicación:
```bash
cd motorRecomendaciones
docker-compose up -d
```

### Servicios Disponibles

- **Aplicación**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **PostgreSQL**: localhost:5432
- **PgAdmin** (si usas el docker-compose raíz): http://localhost:5050

### Credenciales de Base de Datos

- **Database**: motor_recomendaciones_db
- **Usuario**: postgres
- **Contraseña**: Sandia4you

### PgAdmin (opcional)

- **Email**: admin@admin.com
- **Contraseña**: admin

## 🧪 Testing

El proyecto incluye colecciones de Postman en la carpeta `motorRecomendaciones/postman/` para facilitar el testing de los endpoints.

## 📦 Desarrollo Local

Para ejecutar la aplicación sin Docker:

```bash
cd motorRecomendaciones
./mvnw spring-boot:run
```

## 👥 Contribuyentes

Este proyecto ha sido desarrollado por:

- **[José Alberto Cruz](https://github.com/alberto-Cruz-Mtz)** - [@alberto-Cruz-Mtz](https://github.com/alberto-Cruz-Mtz)
- **[KIC8462852B](https://github.com/KIC8462852B)** - [@KIC8462852B](https://github.com/KIC8462852B)
- **[Ivan Lopez Rilopa](https://github.com/irilopa)** - [@irilopa](https://github.com/irilopa)

## 📄 Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o pull request para sugerencias o mejoras.

---

**Equipo 4 - Bytes Colaborativos** 🚀
