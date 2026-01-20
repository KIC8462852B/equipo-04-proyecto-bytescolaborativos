# Motor de Recomendaciones - Bytes Colaborativos

Proyecto desarrollado por el Equipo 4 en la iniciativa Bytes Colaborativos. API REST para recomendaciones personalizadas, con autenticacion y soporte de torneos, construida con Spring Boot y PostgreSQL.

## 🎯 Contexto y objetivo

El objetivo es exponer un servicio de recomendaciones que combine usuarios, productos y calificaciones, con un flujo claro de autenticacion y autorizacion por roles. El trabajo se organizo en equipo, con entregas parciales y foco en que el codigo y la documentacion sean mantenibles.

## 📌 Alcance

Incluye:
- Autenticacion con JWT y roles.
- Gestion de usuarios.
- Gestion de productos y calificaciones.
- Generacion de recomendaciones.
- Gestion de torneos.
- Documentacion interactiva con Swagger.

No incluye:
- Interfaz web.
- Integraciones externas (pagos, terceros, etc.).
- Analitica avanzada o ML en produccion.

## ✅ Requisitos funcionales

- Registrar usuarios y autenticar con JWT.
- Asignar y validar roles de usuario (ADMIN/PLAYER).
- Crear y consultar productos.
- Registrar calificaciones de productos.
- Generar recomendaciones a partir de calificaciones.
- Crear y consultar torneos; aplicar restricciones por rol.
- Exponer documentacion de la API via Swagger/OpenAPI.

## 🧱 Requisitos no funcionales

- Seguridad: secretos fuera del repositorio y JWT en cabecera Authorization.
- Persistencia: PostgreSQL como base de datos principal.
- Portabilidad: despliegue con Docker/Docker Compose.
- Trazabilidad: respuestas de error consistentes.
- Documentacion tecnica: Swagger + README actualizado.
- Configuracion externalizada para entornos distintos.

## 🧭 Casos de uso (resumen)

1) Registro y login de usuario  
Un usuario se registra, inicia sesion y obtiene un JWT para consumir endpoints protegidos.

2) Recomendaciones para un usuario  
Un usuario autentificado solicita recomendaciones, y el sistema responde segun su historial de calificaciones.

3) Gestion de torneos por admin  
Un ADMIN crea o elimina torneos; un usuario PLAYER solo puede consultar.

## 🚀 Tecnologias

- **Backend**: Java con Spring Boot
- **Base de Datos**: PostgreSQL 15
- **Containerizacion**: Docker & Docker Compose
- **Documentacion API**: Swagger/OpenAPI (SpringDoc)
- **Gestion de Dependencias**: Maven

## 🏗️ Estructura del proyecto

```
equipo-04-proyecto-bytescolaborativos/
├── motorRecomendaciones/          # Aplicacion principal
│   ├── src/                       # Codigo fuente
│   ├── pom.xml                    # Configuracion Maven
│   ├── dockerfile                 # Dockerfile de la aplicacion
│   ├── docker-compose.yml         # Docker Compose de la app
│   └── postman/                   # Colecciones Postman para testing
├── docker-compose.yml             # Docker Compose alternativo
├── LICENSE                        # Licencia MIT
└── README.md                      # Este archivo
```

## 🛠️ Instalacion y configuracion

### Prerequisitos

- Docker y Docker Compose instalados
- Java 17 o superior (para desarrollo local)
- Maven (incluido en el proyecto con wrapper)

### Variables de entorno (recomendado)

Configura estas variables:
- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`
- `JWT_SECRET`
- `JWT_EXPIRATION`

### Perfil local (opcional)

Crea `motorRecomendaciones/src/main/resources/application-local.yaml` con tus valores locales (no se commitea) y ejecuta:

```bash
SPRING_PROFILES_ACTIVE=local
```

### Ejecucion con Docker

1) Clonar el repositorio:
```bash
git clone https://github.com/KIC8462852B/equipo-04-proyecto-bytescolaborativos.git
cd equipo-04-proyecto-bytescolaborativos
```

2) Ejecutar con Docker Compose (opcion raiz):
```bash
docker-compose up -d
```

O ejecutar desde el directorio de la aplicacion:
```bash
cd motorRecomendaciones
docker-compose up -d
```

### Servicios disponibles

- **Aplicacion**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **PostgreSQL**: localhost:5432
- **PgAdmin** (si usas el docker-compose raiz): http://localhost:5050

## 🧪 Testing

El proyecto incluye colecciones de Postman en `motorRecomendaciones/postman/` para probar los endpoints.

## 📦 Desarrollo local

```bash
cd motorRecomendaciones
./mvnw spring-boot:run
```

## 🤝 Trabajo en equipo

Para mantener el proyecto coherente entre todos:
- **Planificacion**: backlog y sprints en Taiga.
- **Trabajo en ramas**: una rama por feature/bugfix, PRs cortas y revisables.
- **Revision**: cambios revisados por al menos otro miembro.
- **Definicion de hecho**: compila, endpoints probados, README actualizado si aplica.
- **Registro**: comentarios en tareas al cerrar o reasignar.

## 👥 Contribuyentes

Este proyecto ha sido desarrollado por:
- **[Jose Alberto Cruz](https://github.com/alberto-Cruz-Mtz)** - [@alberto-Cruz-Mtz](https://github.com/alberto-Cruz-Mtz)
- **[KIC8462852B](https://github.com/KIC8462852B)** - [@KIC8462852B](https://github.com/KIC8462852B)
- **[Ivan Lopez Rilopa](https://github.com/irilopa)** - [@irilopa](https://github.com/irilopa)

## 📄 Licencia

Este proyecto esta licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para mas detalles.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Abre un issue o pull request para sugerencias o mejoras.

---

**Equipo 4 - Bytes Colaborativos** 🚀
