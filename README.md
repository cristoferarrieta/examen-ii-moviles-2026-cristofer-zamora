# Panini Support Mobile

Aplicación móvil desarrollada como prueba de concepto para gestionar tickets de soporte relacionados con proveedores, inventario y distribución del álbum Panini FIFA World Cup 2026.

La aplicación permite consultar tickets, revisar detalles, crear nuevas incidencias, actualizar estados, modificar prioridades y controlar funcionalidades mediante Feature Flags desde una pantalla de configuración de prueba.

## Tecnologías utilizadas

* Kotlin
* Jetpack Compose
* Material 3
* MVVM
* StateFlow
* Retrofit para dejar preparada una futura integración con backend
* Mock Repository para trabajar con datos simulados

## Estructura del repositorio

```text
app/
contracts/
docs/
video/
README.md
```

## Estructura principal del proyecto Android

```text
app/app/src/main/java/com/cristofer/paninisupportmobile/core
```

Contiene elementos compartidos como Feature Flags y estados comunes de UI.

```text
app/app/src/main/java/com/cristofer/paninisupportmobile/data
```

Contiene DTOs, API Service, mapper y repositorio mock.

```text
app/app/src/main/java/com/cristofer/paninisupportmobile/domain
```

Contiene los modelos principales del sistema, como tickets, estados, prioridades y categorías.

```text
app/app/src/main/java/com/cristofer/paninisupportmobile/navigation
```

Contiene la navegación simple entre pantallas.

```text
app/app/src/main/java/com/cristofer/paninisupportmobile/ui/screens
```

Contiene las pantallas principales desarrolladas con Jetpack Compose.

```text
app/app/src/main/java/com/cristofer/paninisupportmobile/ui/components
```

Contiene componentes reutilizables de interfaz.

```text
app/app/src/main/java/com/cristofer/paninisupportmobile/ui/viewmodel
```

Contiene los ViewModels de cada pantalla.

```text
contracts/tickets-api.yaml
```

Define el contrato de API previsto para una futura integración con backend.

```text
docs/
```

Contiene la documentación técnica del proyecto: justificación técnica, decisiones arquitectónicas, estrategia basada en eventos, Feature Flags y flujo general del sistema.

```text
video/demo-link.md
```

Contiene el enlace al video demo del proyecto.

## Cómo ejecutar el proyecto

Desde la carpeta `app`, ejecutar:

```bash
./gradlew assembleDebug
```

En Windows:

```powershell
.\gradlew.bat assembleDebug
```

Luego abrir el proyecto en Android Studio y ejecutar la aplicación en un emulador o dispositivo físico.

## Funcionalidades implementadas

* Inicio de sesión simulado con validación básica.
* Listado de tickets usando `LazyColumn`.
* Visualización del detalle de un ticket.
* Creación de nuevos tickets.
* Actualización del estado del ticket.
* Actualización de prioridad.
* Ordenamiento por prioridad: Alta, Media y Baja.
* Actualización reactiva del listado mediante `StateFlow`.
* Datos simulados relacionados con proveedores, inventario y distribución de Panini.
* Pantalla de configuración de prueba para controlar Feature Flags.
* Capa de networking preparada con Retrofit, DTOs y contratos YAML.

## Estados de ticket

La aplicación maneja los siguientes estados:

* Abierto
* En proceso
* Resuelto
* Cerrado

## Prioridades

La aplicación maneja tres niveles de prioridad:

* Alta
* Media
* Baja

Los tickets se ordenan mostrando primero los de prioridad alta.

## Feature Flags

La aplicación incluye dos Feature Flags principales:

### `enableTicketCreation`

Controla si el usuario puede crear tickets.

* Activado: muestra el acceso para crear tickets.
* Desactivado: oculta o bloquea el acceso al flujo de creación.

### `enablePriorityUpdate`

Controla si el usuario puede modificar la prioridad de un ticket.

* Activado: permite cambiar la prioridad desde el detalle.
* Desactivado: muestra la prioridad actual, pero no permite modificarla.

Estas opciones pueden modificarse desde la pantalla de configuración de prueba incluida en la app.

## Consideraciones técnicas

La aplicación usa una estructura MVVM simple. Las pantallas se encargan de la interfaz, los ViewModels manejan el estado y el repositorio concentra las operaciones sobre tickets.

El repositorio mock mantiene los datos en memoria y expone la lista de tickets mediante `StateFlow`. Esto permite que la creación de tickets, los cambios de estado y los cambios de prioridad se reflejen en la interfaz sin hacer recargas manuales.

Aunque no se consume un backend real, se incluyó una estructura básica de integración con Retrofit, DTOs, mapper y contratos YAML. Esto permite que el proyecto pueda continuar en otra etapa reemplazando el repositorio mock por una implementación conectada a una API.
