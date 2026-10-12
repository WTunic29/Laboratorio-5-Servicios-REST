# Laboratorio 5 — Servicios REST
## Muebles Los Alpes

Implementación individual del laboratorio de servicios REST para **Muebles Los Alpes**. El proyecto parte de la estructura proporcionada por el profesor y adapta sus componentes para ejecutarse en un entorno Jakarta EE moderno. También amplía el servicio de registro con una operación REST `PUT` y documenta las pruebas realizadas.

## Enlaces

- **Colección pública de Postman:** https://go.postman.co/workspace/38ce2a9f-d92c-4dfd-b1f7-f442b6782eb1
- **Repositorio GitHub:** https://github.com/WTunic29/Laboratorio-5-Servicios-REST

## Objetivo

Implementar y verificar un servicio REST para consultar, registrar, actualizar y eliminar clientes, reutilizando la lógica EJB y la persistencia simulada del proyecto.

## Tecnologías y entorno

- Java 17
- Jakarta EE 10
- Apache NetBeans IDE 31
- GlassFish 7.1.1
- Proyectos Ant de NetBeans
- Jakarta Enterprise Beans (EJB)
- Jakarta RESTful Web Services (JAX-RS)
- JSON para solicitudes y respuestas HTTP
- Postman para pruebas
- Git y GitHub para control de versiones

El proyecto de referencia utilizaba APIs Java EE antiguas (`javax.*`) y una configuración REST anterior. Esta entrega se adaptó a las APIs `jakarta.*` del entorno usado para ejecutar el laboratorio.

## Estructura

```text
MueblesLosAlpes/
├── MueblesLosAlpes-ejb/
│   └── src/java/co/edu/uniandes/csw/mueblesdelosalpes/
│       ├── dto/
│       ├── excepciones/
│       ├── logica/ejb/
│       ├── logica/interfaces/
│       └── persistencia/mock/
├── MueblesLosAlpes-war/
│   └── src/java/co/edu/uniandes/csw/mueblesdelosalpes/servicios/
│       ├── RegistroService.java
│       └── RestApplication.java
├── MueblesLosAlpes.postman_collection.json
├── build.xml
└── README.md
```

- **`MueblesLosAlpes-ejb`** contiene DTO, interfaces locales/remotas, la excepción de negocio, el servicio de registro EJB y la persistencia simulada.
- **`MueblesLosAlpes-war`** expone el recurso REST mediante JAX-RS.
- **`MueblesLosAlpes.postman_collection.json`** contiene las solicitudes de prueba utilizadas.

## Implementación y cambios frente al proyecto del profesor

### 1. Servicio de registro (`ServicioRegistroMock`)

Se conectó el servicio de registro con la persistencia simulada mediante inyección EJB.

- `registrar(Usuario)`: valida que el usuario no sea nulo y que el documento tenga un valor válido antes de solicitar su creación.
- `eliminarCliente(String login)`: busca el cliente por login y lanza `OperacionInvalidaException` si no existe.
- `darClientes()`: devuelve la lista de usuarios de la persistencia simulada.
- `actualizarCliente(Usuario)`: **ampliación de esta entrega** para permitir la actualización de clientes. Valida el objeto y el login y comunica mediante una excepción cuando no se encuentra el usuario.

### 2. Interfaces EJB

Se conservaron las interfaces locales y remotas de registro y persistencia. Se añadieron o ajustaron las anotaciones `@Local` y `@Remote` para que el contenedor EJB pudiera resolver correctamente los servicios.

La firma de `actualizarCliente` declara `OperacionInvalidaException` en las interfaces de registro correspondientes, para propagar los errores de negocio.

### 3. Persistencia simulada (`ServicioPersistenciaMock`)

Se conserva la persistencia en memoria del proyecto de referencia; **no se incorporó una base de datos real**. La implementación maneja colecciones de usuarios, vendedores, muebles y registros de venta.

Mejoras realizadas:

- **Identificadores de vendedores:** se incorporó `siguienteIdVendedor()` para calcular el próximo identificador a partir del mayor ID existente, en vez de depender solamente del tamaño de la lista.
- **Referencias de muebles:** se incorporó `siguienteReferenciaMueble()` con el mismo criterio para evitar reutilizar una referencia si hay huecos en la secuencia.
- **Resultado de actualización explícito:** `update(Object)` cambió de `void` a `boolean`. Devuelve `true` si encuentra y reemplaza el objeto, o `false` si no puede actualizarlo.
- **Actualización segura de usuarios:** se comprueba que el login no sea nulo antes de comparar y reemplazar usuarios.
- **Lista de compras de `Usuario`:** se inicializa la lista y se maneja de forma segura una lista nula.
- **Eliminación de clientes:** el servicio de registro comprueba si el usuario existe antes de solicitar su eliminación.

### 4. Recurso REST (`RegistroService`)

Se implementó el recurso REST bajo la ruta `/Registro`, con JSON como formato de intercambio. `RestApplication` define el prefijo `/webresources`.

| Método | Endpoint | Función | Resultado comprobado |
|---|---|---|---|
| `GET` | `/Registro/clientes/` | Consultar clientes | `200 OK` |
| `POST` | `/Registro/clientes/` | Registrar un cliente | `201 Created` |
| `PUT` | `/Registro/clientes/{login}` | Actualizar un cliente existente | `200 OK` |
| `PUT` | `/Registro/clientes/{login}` | Actualizar un cliente inexistente | `404 Not Found` |
| `DELETE` | `/Registro/clientes/{login}` | Eliminar un cliente | `204 No Content` |

URL base utilizada durante las pruebas locales:

```text
http://localhost:8080/MueblesLosAlpes-war/webresources
```

Ejemplo de consulta de clientes:

```text
http://localhost:8080/MueblesLosAlpes-war/webresources/Registro/clientes/
```

#### Corrección del caso de actualización fallida

Inicialmente, el método `PUT` podía devolver `200 OK` aunque el cliente no existiera. Se corrigió así:

1. La persistencia informa si encontró y actualizó el usuario mediante un valor booleano.
2. `ServicioRegistroMock` lanza `OperacionInvalidaException` cuando no se realiza la actualización.
3. `RegistroService` captura esa excepción y devuelve `404 Not Found`.

Si el cuerpo de la solicitud es nulo, el recurso responde `400 Bad Request`.

### 5. Adaptación tecnológica

Los cambios principales para ejecutar el proyecto en el entorno actual fueron:

- Migración de importaciones de Java EE `javax.*` a APIs Jakarta `jakarta.*` donde corresponde.
- Adaptación de EJB y REST a Jakarta EE 10 y GlassFish 7.1.1.
- Corrección de la configuración de las interfaces EJB para permitir la inyección.
- Incorporación de `PUT` para completar el CRUD de clientes; el servicio de registro original se centraba en registrar, eliminar y consultar clientes.
- Mejora de la generación de identificadores y del control del resultado de actualización.

La operación `PUT` y las mejoras descritas son ampliaciones de esta entrega, no funcionalidades que deban atribuirse al código original del profesor.

## DTO y modelos

El módulo EJB contiene los modelos utilizados por los servicios:

- `Usuario`
- `Vendedor`
- `Mueble`
- `RegistroVenta`
- `Ciudad`
- `Pais`
- `ExperienciaVendedor`
- Enumeraciones `TipoUsuario`, `TipoDocumento`, `TipoMueble` y `Profesion`

`OperacionInvalidaException` se utiliza para comunicar errores de negocio entre la capa EJB y el recurso REST.

## Pruebas realizadas con Postman

Las pruebas se ejecutaron contra la aplicación desplegada localmente en GlassFish.

| Caso | Resultado observado |
|---|---|
| Consultar clientes (`GET`) | `200 OK` |
| Registrar cliente (`POST`) | `201 Created` |
| Actualizar cliente existente (`PUT`) | `200 OK` |
| Actualizar cliente inexistente (`PUT`) | `404 Not Found` y mensaje de error |
| Eliminar cliente (`DELETE`) | `204 No Content` |
| Consultar nuevamente (`GET`) | `200 OK`; se comprobó el nombre actualizado y, en la prueba de eliminación, la ausencia del cliente eliminado |

La colección pública contiene las solicitudes y las respuestas guardadas:

**[Abrir la colección de Postman](https://go.postman.co/workspace/38ce2a9f-d92c-4dfd-b1f7-f442b6782eb1)**

## Cómo ejecutar el proyecto

1. Instalar un JDK compatible; esta entrega se compiló con Java 17.
2. Configurar GlassFish 7.1.1 en Apache NetBeans IDE 31.
3. Abrir el proyecto principal `MueblesLosAlpes`.
4. Ejecutar **Clean and Build** para `MueblesLosAlpes-ejb`.
5. Ejecutar **Clean and Build** para `MueblesLosAlpes-war`.
6. Ejecutar **Clean and Build** para el proyecto principal `MueblesLosAlpes`.
7. Ejecutar el proyecto para desplegar el EAR en GlassFish.
8. Abrir la colección enlazada en Postman y ejecutar las solicitudes.

Durante las pruebas, los tres módulos finalizaron con `BUILD SUCCESSFUL`.

## Limitaciones conocidas

- La persistencia es simulada y utiliza colecciones en memoria. Los datos pueden reiniciarse al reiniciar GlassFish o volver a desplegar la aplicación.
- Las pruebas REST de clientes no demuestran por sí solas el comportamiento de las operaciones de persistencia de vendedores y muebles. Los métodos auxiliares de generación de identificadores están implementados, pero no se creó un endpoint REST para probarlos.
- La URL `localhost` solo funciona en el equipo donde se ejecuta GlassFish; no es un servicio publicado en Internet.
- No se incorporó una base de datos persistente ni autenticación/autorización REST como parte de los cambios descritos.

## Repositorio y entrega

- **GitHub:** https://github.com/WTunic29/Laboratorio-5-Servicios-REST
- **Postman:** https://go.postman.co/workspace/38ce2a9f-d92c-4dfd-b1f7-f442b6782eb1

---

**Estado:** compilación satisfactoria de los tres módulos y pruebas manuales de los endpoints REST de clientes mediante Postman.
