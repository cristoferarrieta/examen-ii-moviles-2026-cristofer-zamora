# Estrategia basada en eventos

La aplicación usa una comunicación reactiva basada en `StateFlow` para que los cambios en los tickets se reflejen en la interfaz sin hacer recargas manuales.

La fuente de datos principal es `MockTicketRepository`. Este repositorio mantiene la lista actual de tickets en un `MutableStateFlow<List<Ticket>>` y la expone como `StateFlow<List<Ticket>>`. De esta forma, los ViewModels pueden observar los cambios, pero no modificar la lista directamente.

## Actualización del listado

`TicketListViewModel` observa el flujo de tickets del repositorio. Cada vez que la lista cambia, el ViewModel genera un nuevo estado para la pantalla y ordena los tickets por prioridad:

1. Alta
2. Media
3. Baja

Esto permite que el listado siempre muestre primero los tickets más urgentes.

## Creación de tickets

Cuando el usuario crea un ticket, `CreateTicketViewModel` valida los campos requeridos y llama a `repository.createTicket(...)`.

Después, el repositorio agrega el nuevo ticket a la lista interna y actualiza el `MutableStateFlow`. Como `TicketListViewModel` está observando ese flujo, el nuevo ticket aparece en el listado sin tener que reiniciar la pantalla ni agregar una acción manual de refresco.

## Actualización de prioridad

Cuando el usuario cambia la prioridad desde el detalle del ticket, `TicketDetailViewModel` llama a `repository.updatePriority(...)`.

El repositorio actualiza el ticket correspondiente y emite una nueva lista. Al recibir ese cambio, `TicketListViewModel` vuelve a ordenar los tickets. Por eso, si un ticket pasa de prioridad Baja o Media a Alta, puede moverse automáticamente hacia la parte superior del listado.

## Actualización del detalle

`TicketDetailViewModel` también trabaja con el mismo repositorio compartido. Por eso, cuando cambia el estado o la prioridad de un ticket, el detalle se mantiene sincronizado con la información actual del repositorio.

## Motivo de la decisión

Se eligió `StateFlow` porque permite cumplir el comportamiento reactivo solicitado sin agregar una arquitectura más compleja. Para esta PoC, no era necesario usar un bus de eventos, base de datos local ni sincronización con backend.

La solución se mantiene simple: el repositorio en memoria funciona como fuente de verdad y los ViewModels reaccionan a los cambios que este emite.
