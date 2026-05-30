# Justificación técnica

Esta aplicación se desarrolló como una primera propuesta móvil para manejar tickets de soporte internos relacionados con la operación de Panini y la distribución del álbum FIFA World Cup 2026.

El problema que se busca atender es el seguimiento de incidencias con proveedores, inventario y entregas. Cuando este tipo de reportes se manejan por correos, hojas de cálculo o mensajes sueltos, es fácil perder el control de qué casos están abiertos, cuáles ya se están atendiendo y cuáles tienen mayor prioridad.

Por eso, la app se enfocó en un flujo básico: iniciar sesión de forma simulada, ver el listado de tickets, revisar el detalle de una incidencia, crear nuevos tickets, cambiar el estado y ajustar la prioridad cuando sea necesario.

Para esta versión se trabajó con datos simulados en memoria. Los tickets usados representan situaciones del contexto de Panini, como faltantes de sobres, entregas retrasadas, cajas dañadas o diferencias de inventario. Esto permite probar el comportamiento principal de la app sin depender todavía de un backend.

Aunque la app no consume una API real, se dejó preparada una estructura de integración con Retrofit, DTOs y el contrato `contracts/tickets-api.yaml`. La idea es que, si el proyecto continúa, el repositorio mock pueda reemplazarse por una implementación conectada a un servicio real sin cambiar toda la interfaz.

No se agregó autenticación real, base de datos local ni persistencia porque en esta etapa no eran necesarias para validar el flujo principal. La prioridad fue mantener una base simple, entendible y fácil de modificar por otro desarrollador.
