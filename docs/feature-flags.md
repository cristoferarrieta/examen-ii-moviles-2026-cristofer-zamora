# Feature Flags

Los Feature Flags están centralizados en `core/featureflags/FeatureFlags.kt`.

Para esta PoC se usaron constantes simples, ya que el objetivo era poder activar o desactivar funcionalidades puntuales sin agregar servicios externos, configuración remota o una pantalla administrativa.

## `enableTicketCreation`

Controla el acceso al flujo de creación de tickets.

- Si está en `true`, se muestra el FAB de crear ticket y se permite navegar a la pantalla de creación.
- Si está en `false`, el FAB no se muestra y la navegación hacia creación queda bloqueada desde la app.

## `enablePriorityUpdate`

Controla si el usuario puede modificar la prioridad de un ticket desde la pantalla de detalle.

- Si está en `true`, se muestra el selector de prioridad y se permite cambiar entre Alta, Media y Baja.
- Si está en `false`, el selector no aparece. El usuario puede ver la prioridad actual, pero no modificarla.

## Motivo de la decisión

Se implementaron como constantes porque el alcance de la aplicación es una prueba de concepto. Para este escenario no era necesario usar Remote Config ni una solución dinámica de administración de flags.

La ventaja de este enfoque es que el comportamiento queda centralizado y es fácil de probar: basta cambiar el valor en `FeatureFlags.kt` para activar o desactivar cada funcionalidad.

Esta decisión mantiene la solución simple, evita dependencias innecesarias y cumple con el objetivo de permitir pruebas internas rápidas sobre funcionalidades específicas.