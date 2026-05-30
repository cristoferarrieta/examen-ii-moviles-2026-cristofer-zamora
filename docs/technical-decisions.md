# Decisiones técnicas

## MVVM

La app usa una estructura MVVM simple porque mantiene los composables enfocados en la interfaz y los eventos del usuario. Los ViewModels conservan el estado de pantalla y llaman al repositorio para las operaciones de tickets, lo que hace que la demo sea fácil de explicar sin agregar capas innecesarias.

## Repositorio mock

`MockTicketRepository` mantiene los tickets de soporte en memoria. Esto es suficiente para la PoC del examen porque la app no necesita persistencia ni backend real, pero la interfaz del repositorio deja preparada la app para una implementación con red más adelante.

## Comunicación reactiva

El repositorio posee un `MutableStateFlow<List<Ticket>>` y lo expone como `StateFlow<List<Ticket>>`. Los ViewModels de lista y detalle observan ese flujo, por lo que los cambios se reflejan automáticamente en la UI.

## Creación de tickets

Cuando se guarda un ticket, el ViewModel de creación valida los campos obligatorios y llama a `repository.createTicket(...)`. El repositorio agrega el nuevo ticket al flujo y la pantalla de lista recibe la lista actualizada inmediatamente.

## Reordenamiento por prioridad

`TicketListViewModel` ordena los tickets por prioridad usando Alta, Media y Baja. Cuando la pantalla de detalle cambia la prioridad de un ticket, el repositorio actualiza el flujo y el ViewModel de lista emite la lista reordenada.

## Feature Flags

Los feature flags viven en `core/featureflags/FeatureFlags.kt`. `enableTicketCreation` controla el acceso al flujo de creación de tickets: si está activo se muestra el FAB y se permite navegar a la pantalla de creación; si está inactivo se oculta el FAB y la navegación queda bloqueada desde la app.

`enablePriorityUpdate` controla si se permite modificar prioridades: si está activo se muestra el selector de prioridad en el detalle; si está inactivo el usuario solo ve la prioridad actual.

Esta estrategia fue elegida por ser simple, mantenible y suficiente para una PoC/MVP. No se usó Remote Config ni una solución más compleja para evitar sobreingeniería.

## Integración futura con backend

La capa de datos incluye DTOs, un mapper y un `TicketApiService` de Retrofit con los endpoints de `contracts/tickets-api.yaml`. La app sigue usando datos mock, pero el límite de red queda preparado.

## Complejidad evitada

La app evita intencionalmente Room, Firebase, inyección de dependencias, Remote Config y frameworks de navegación complejos. La navegación manual y el StateFlow en memoria son suficientes para el comportamiento requerido.
