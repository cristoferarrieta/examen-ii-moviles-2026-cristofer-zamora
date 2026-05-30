# Decisiones arquitectónicas

La aplicación se organizó con una estructura MVVM simple. La intención fue separar la interfaz, el estado de las pantallas y las operaciones sobre tickets sin agregar más capas de las necesarias.

En esta PoC, las pantallas hechas con Jetpack Compose solo se encargan de mostrar información y capturar acciones del usuario. Por ejemplo, cuando el usuario presiona guardar, cambiar estado o modificar prioridad, la pantalla no actualiza los datos directamente; esa acción se envía al ViewModel correspondiente.

Los ViewModels mantienen el estado de cada pantalla y se comunican con el repositorio. Esto permite que la lógica principal del flujo de tickets no quede mezclada dentro de los composables.

## Organización de paquetes

La estructura se mantuvo directa para que otro desarrollador pueda ubicar rápido cada parte del proyecto:

* `ui/screens`: contiene las pantallas principales de la aplicación, como inicio de sesión, listado, detalle y creación de tickets.
* `ui/components`: contiene elementos reutilizables de interfaz, como tarjetas de tickets, badges, estados de carga, error y pantalla vacía.
* `ui/viewmodel`: contiene los ViewModels que manejan el estado y las acciones de cada pantalla.
* `domain/model`: contiene los modelos principales del sistema, como `Ticket`, `TicketStatus`, `TicketPriority` y `TicketCategory`.
* `data`: contiene los DTOs, el mapper, el servicio Retrofit y el repositorio usado por la app.
* `navigation`: contiene las rutas y navegación entre pantallas.
* `core`: contiene elementos compartidos, como los Feature Flags y estados comunes de UI.

Esta separación evita que una sola pantalla tenga demasiada responsabilidad y permite mantener el código más fácil de leer.

## Repositorio

Se definió `TicketRepository` como interfaz para no acoplar los ViewModels a una implementación específica. Durante esta prueba se usa `MockTicketRepository`, pero la idea es que en una fase posterior pueda existir otro repositorio conectado a un backend real.

`MockTicketRepository` mantiene los tickets en memoria y permite realizar las operaciones necesarias para el MVP:

* consultar tickets;
* crear tickets;
* actualizar estado;
* actualizar prioridad.

Esto es suficiente para demostrar el flujo funcional de la aplicación sin depender de una API real.

## Integración futura

Aunque la app usa datos simulados, se dejó una base para integración posterior mediante:

* DTOs;
* mapper;
* `TicketApiService` con Retrofit;
* contratos YAML en `/contracts`.

Con esto, el siguiente paso natural sería implementar un repositorio remoto que use `TicketApiService`, manteniendo la misma interfaz `TicketRepository`.

## Complejidad evitada

No se usaron módulos separados, Firebase, Hilt, Koin ni autenticación real porque no eran necesarios para el alcance de la prueba.

El objetivo era entregar una PoC clara, funcional y fácil de continuar. Agregar esas herramientas habría aumentado la complejidad sin aportar directamente a los requerimientos solicitados: tickets, flujo reactivo, Feature Flags, mock integration y preparación para backend.
