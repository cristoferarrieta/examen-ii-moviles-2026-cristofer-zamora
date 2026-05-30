# Flujo general del sistema

El flujo inicia con un login simulado. La pantalla valida que el correo y la contraseña no estén vacíos; si la validación pasa, la app navega al listado de tickets. No hay autenticación real ni token porque la PoC se enfoca en la gestión de tickets.

El listado muestra los tickets actuales del repositorio mock. Cada tarjeta presenta título, proveedor, categoría, fecha, estado y prioridad. La lista se ordena por prioridad para mostrar primero los casos de mayor atención: Alta, luego Media y luego Baja.

Al seleccionar un ticket, la app navega al detalle. Esa pantalla muestra la información principal del caso: proveedor, categoría, fecha de creación, descripción, estado y prioridad.

Desde el detalle se puede actualizar el estado del ticket entre Abierto, En proceso, Resuelto y Cerrado. El cambio se aplica en el repositorio y se refleja en la UI mediante el flujo reactivo.

La prioridad también puede actualizarse desde el detalle cuando `enablePriorityUpdate` está activo. Al cambiar entre Alta, Media y Baja, el repositorio emite la nueva lista y el listado se reordena automáticamente.

La creación de tickets está disponible cuando `enableTicketCreation` está activo. El formulario valida título, proveedor y descripción, y luego agrega el nuevo ticket al repositorio mock con estado inicial Abierto.

Como el repositorio expone los tickets con `StateFlow`, las pantallas reciben los cambios sin recargar manualmente. Crear un ticket, cambiar estado o cambiar prioridad actualiza la fuente de datos en memoria y la UI observa esos cambios.
