# Feature Flags

Los Feature Flags están centralizados en `core/featureflags/FeatureFlags.kt`.

Para esta PoC se usan propiedades observables con `mutableStateOf`. Esto permite que Compose actualice la interfaz cuando un flag cambia, sin agregar persistencia, backend ni Remote Config.

## Configuración de prueba

La app incluye una pantalla simple llamada "Configuración de prueba", accesible desde el listado de tickets. Esta pantalla permite activar o desactivar funcionalidades durante pruebas internas de la PoC.

Los cambios viven solo en memoria. Si la app se cierra, los flags vuelven a sus valores iniciales.

## `enableTicketCreation`

Controla el acceso al flujo de creación de tickets.

- Si está en `true`, se muestra el FAB para crear ticket y se permite navegar a la pantalla de creación.
- Si está en `false`, el FAB no se muestra y la navegación hacia creación queda bloqueada desde la app.

En la pantalla de configuración se controla con el switch "Permitir creación de tickets".

## `enablePriorityUpdate`

Controla si el usuario puede modificar la prioridad de un ticket desde la pantalla de detalle.

- Si está en `true`, se muestra el selector de prioridad y se permite cambiar entre Alta, Media y Baja.
- Si está en `false`, el selector no aparece. El usuario puede ver la prioridad actual, pero no modificarla.

En la pantalla de configuración se controla con el switch "Permitir actualización de prioridades".

## Motivo de la decisión

Se eligió estado local en memoria porque es suficiente para una PoC/MVP de examen: permite probar el comportamiento real de los flags desde la interfaz sin introducir infraestructura adicional.

No se usó Remote Config, DataStore ni persistencia local para evitar complejidad innecesaria. La intención es validar el flujo y demostrar que los flags afectan la UI y el comportamiento de la app.
